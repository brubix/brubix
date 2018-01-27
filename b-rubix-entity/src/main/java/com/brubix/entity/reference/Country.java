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
@Table(name = "country", catalog = "brubix")
public class Country {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, length = 3, unique = true)
    private String code;

    @Column(name = "description", nullable = false, length = 20)
    private String description;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<State> states;

    @Column(name = "dialing_code", nullable = false, length = 5)
    private String dialingCode;
}
