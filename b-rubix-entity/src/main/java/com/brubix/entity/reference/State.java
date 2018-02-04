package com.brubix.entity.reference;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Getter
@Setter
@Entity
@Table(name = "state", catalog = "brubix")
public class State {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    @Column(name = "code", nullable = false, length = 3, unique = true)
    private String code;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities;

}
