package com.brubix.model.result;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */


@Entity
@Table(name = "grade", catalog = "brubix")
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    // TODO - relation between grades and course is one to many ?
    //private List<Course> subjects;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
