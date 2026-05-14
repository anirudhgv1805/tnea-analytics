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
@Table(name = "cutoffs")
public class Cutoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    private Double oc;
    private Double bc;
    private Double bcm;
    private Double mbc;
    private Double sc;
    private Double sca;
    private Double st;
    private boolean bc_partial;
    private boolean oc_partial;
    private boolean bcm_partial;
    private boolean mbc_partial;
    private boolean sc_partial;
    private boolean sca_partial;
    private boolean st_partial;
}
