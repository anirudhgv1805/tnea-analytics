package com.tneaanalytics.backend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateUserException extends RuntimeException {
    private String message;

    public DuplicateUserException(String message) {
        super(message);
        this.message = message;
    }

}
