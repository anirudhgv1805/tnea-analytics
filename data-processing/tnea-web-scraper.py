import requests
import json
import base64
import json
from hashlib import sha256
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
import argparse
from pathlib import Path
from tqdm import tqdm
from datetime import datetime
import os


# Captcha Token 
BEARER_TOKEN = os.getenv("BEARER_TOKEN")
MAX_COURSES_PER_PAGE = 200


def makeRequest(URL, BEARER_TOKEN):
    response = requests.get(URL, headers={"Authorization": f"Bearer {BEARER_TOKEN}"})

    if response.status_code != 200:
        raise Exception(f"Request failed with status code {response.status_code}")

    return response.json()


def decryptAPIResponse(response, token):
    if not response.get("_enc"):
        return response

    key_source = "tnea-portal-aes-2026-static" + token
    key = sha256(key_source.encode()).digest()

    iv = base64.b64decode(response["iv"])
    ciphertext = base64.b64decode(response["data"])

    cipher = AES.new(key, AES.MODE_CBC, iv)
    decrypted_bytes = cipher.decrypt(ciphertext)

    decrypted = unpad(decrypted_bytes, AES.block_size)

    return json.loads(decrypted.decode("utf-8"))


def getTotalRoundsandEntries(cutoff_type: str, year: int, pageno=1) -> tuple[int, int]:
    URL = f"https://cutoff.tneaonline.org/api/cutoff?type={cutoff_type}&year={year}&page={pageno}&pageSize=200"

    total_courses: int = decryptAPIResponse(
        makeRequest(URL, BEARER_TOKEN), BEARER_TOKEN
    )["total"]
    total_rounds: int = total_courses // MAX_COURSES_PER_PAGE

    if total_courses % MAX_COURSES_PER_PAGE != 0:
        total_rounds += 1

    return total_rounds, total_courses


def dump_into_json(decrypted_json: dict, file_name: str, save_path: str, year: int):
    path = Path(save_path + str(year))
    path.mkdir(parents=True, exist_ok=True)

    file_path = path / f"{file_name}.json"
    with open(file_path, "w", encoding="utf-8") as f:
        json.dump(decrypted_json, f, ensure_ascii=False, indent=4)


def fetchData(cutoff_type: str, year: int, save_location):

    total_rounds, total_entries = getTotalRoundsandEntries(cutoff_type, year, 1)

    # To fetch all the college data at a stretch we are just going to take the data and append it to the json page by page
    pageno = 0

    decrypted_data = []
    with tqdm(
        total=total_entries, desc=f"Fetching {cutoff_type} for the year : {year}",ascii="░█"
    ) as pbar:
        while len(decrypted_data) < total_entries:
            pageno += 1
            URL = f"https://cutoff.tneaonline.org/api/cutoff?type={cutoff_type}&year={year}&page={pageno}&pageSize=200"
            response = makeRequest(URL, BEARER_TOKEN)

            decrypted_response = decryptAPIResponse(response, BEARER_TOKEN)

            decrypted_data.extend(decrypted_response["data"])
            pbar.update(len(decrypted_response["data"]))

        data_to_dump = {"data": decrypted_data, "total_entries": total_entries}
        dump_into_json(data_to_dump, f"{cutoff_type}{year}", save_location, year)
        decrypted_response = []


def main(year: int, save_location="."):

    if year == 0:
        current_year = datetime.now().year
        for year in range(current_year - 5, current_year):
            fetchData(cutoff_type="cutoff", year=year, save_location=save_location)
            fetchData(cutoff_type="rank", year=year, save_location=save_location)
            fetchData(cutoff_type="allotments", year=year, save_location=save_location)
    else:
        fetchData(cutoff_type="cutoff", year=year, save_location=save_location)
        fetchData(cutoff_type="rank", year=year, save_location=save_location)
        fetchData(cutoff_type="allotments", year=year, save_location=save_location)


if __name__ == "__main__":

    # For command line tooling
    parser = argparse.ArgumentParser(
        description="TNEA Cutoff Fetching tool, fetches cutoff, allotment and rank details of all colleges"
    )
    parser.add_argument(
        "year",
        type=int,
        help="year needed to be fetched, enter upto only last 5 years, mention as 0 if u want last 5 years to be fetched",
    )
    parser.add_argument(
        "save_location",
        type=str,
        default="",
        nargs="?",
        help="Creates and saves if there is no directory",
    )
    args = parser.parse_args()

    main(args.year, args.save_location)
