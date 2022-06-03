package com.test.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "st_benefit_type")
@Getter
@Setter

public class BenefitType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "default_amount", nullable = false)
    private double default_amount;
}
