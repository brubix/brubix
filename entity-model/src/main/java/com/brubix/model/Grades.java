package com.brubix.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */

public class Grades {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Integer id;
    List<Course> subjects;

    @Column(name = "name", unique = true, nullable = false)
    String name;


    private class Course{
        List<Subject> subjects;
        String description;
    }

    private class Subject{
        String name;
        String description;
    }
}
