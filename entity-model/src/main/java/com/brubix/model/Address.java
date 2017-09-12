package com.brubix.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "subject", catalog = "bigrubix")
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

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "pin_code", length = 6)
    private String pinCode;
}
