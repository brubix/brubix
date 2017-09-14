package com.brubix.model;



import com.brubix.model.enums.Country;
import com.brubix.model.enums.State;

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
    private Integer id;

    @Column(name = "first_line", nullable = false, length = 50)
    private String firstLine;

    @Column(name = "second_line", length = 50)
    private String secondLine;

    @Column(name = "third_line", length = 50)
    private String thirdLine;

    @Column(name = "country", nullable = false, length = 50)
    private Country country;

    @Column(name = "state", nullable = false, length = 50)
    private State state;

    @Column(name = "pin_code", length = 6)
    private String pinCode;
}
