package com.brubix.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */


@Entity
@Table(name = "grades", catalog = "brubix")
@Getter
@Setter
public class Grades {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    // TODO - relation between grades and course is one to many ?
    //private List<Course> subjects;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
