package com.brubix.entity.reference;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "language", catalog = "brubix")
public class Language {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "language", nullable = false, length = 100, unique = true)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;
}
