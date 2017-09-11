package com.brubix.model;

import com.brubix.model.enums.Subject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */

public class Classes {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Integer id;
    List<Subject> subjects;

    @Column(name = "name", unique = true, nullable = false)
    String name;
}
