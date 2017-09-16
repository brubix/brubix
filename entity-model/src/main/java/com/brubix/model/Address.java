package com.brubix.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "address", catalog = "brubix")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_line", nullable = false, length = 50)
    private String firstLine;

    @Column(name = "second_line", length = 50)
    private String secondLine;

    @Column(name = "third_line", length = 50)
    private String thirdLine;

    @OneToOne(fetch = FetchType.LAZY)
    private State state;

    @OneToOne(fetch = FetchType.LAZY)
    private Country country;

    @Column(name = "pin_code", length = 6)
    private String pinCode;
}
