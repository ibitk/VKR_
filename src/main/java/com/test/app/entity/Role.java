package com.test.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "st_role")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    public Role(final String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name", length = 40, nullable = false)
    private String name;
}

