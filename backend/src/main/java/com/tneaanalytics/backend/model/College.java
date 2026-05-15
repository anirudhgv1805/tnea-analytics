package com.tneaanalytics.backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "college")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int counsellingCode;

    private String name;

    private String category;

    private String district;

    private String webLink;

    @OneToMany(mappedBy = "college")
    private List<Branch> branches;

    @OneToMany(mappedBy = "college")
    private List<Cutoff> cutoffs;

    // Later point: want to add map coordinates of the college
    Double latitude;
    Double longittude;

}
