package com.brubix.model;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "students", catalog = "bigrubix")
public class Students {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Integer id;

    Date dateOfAdmission;

    Date dateOfPassout;

}
