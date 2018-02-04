package com.brubix.entity.reference;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "institution_type", catalog = "brubix")
public class InstitutionType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    @Column(name = "description", nullable = false, length = 255)
    private String description;
}
