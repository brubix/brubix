package com.brubix.model;



import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "subject", catalog = "bigrubix")
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Integer id;

    @Column(name = "first_line", nullable = false, length = 50)
    String firstLine;

    @Column(name = "second_line", length = 50)
    String secondLine;

    @Column(name = "third_line", length = 50)
    String thirdLine;

    @Column(name = "country", nullable = false, length = 50)
    String country;

    @Column(name = "state", nullable = false, length = 50)
    String state;

    @Column(name = "pin_code", length = 6)
    String pinCode;
}
