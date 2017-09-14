package com.brubix.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "teachers", catalog = "bigrubix")
public class Teachers {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Integer id;
    private Person person;

    @Column(name = "joining_date", nullable = false)
    private Date joingDate;

    @Column(name = "resignation_date", nullable = false)
    private Date resignationDate;



}
