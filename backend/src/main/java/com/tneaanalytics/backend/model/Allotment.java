package com.tneaanalytics.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "allotments")
public class Allotment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;


    // Seat counts alloted 
    private int oc_initial;
    private int bc_initial;
    private int bcm_initial;
    private int mbc_initial;
    private int sc_initial;
    private int sca_initial;
    private int st_initial;

    // Total filled seats
    private int bc_filled;
    private int oc_filled;
    private int bcm_filled;
    private int mbc_filled;
    private int sc_filled;
    private int sca_filled;
    private int st_filled;
}
