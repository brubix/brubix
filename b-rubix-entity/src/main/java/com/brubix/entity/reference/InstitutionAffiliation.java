package com.brubix.entity.reference;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "institution_affiliation", catalog = "brubix")
public class InstitutionAffiliation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "affiliation", nullable = false, length = 100, unique = true)
    private String affiliation;

    @Column(name = "description", nullable = false)
    private String description;
}
