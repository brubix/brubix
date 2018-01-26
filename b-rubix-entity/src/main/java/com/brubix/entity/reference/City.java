package com.brubix.entity.reference;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sanjaya on 26/01/18.
 */
@Getter
@Setter
@Entity
@Table(name = "city", catalog = "brubix")
public class City {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private State state;

    @Column(name = "code", nullable = false, length = 3, unique = true)
    private String code;

    @Column(name = "description", nullable = false, length = 20)
    private String description;
}
