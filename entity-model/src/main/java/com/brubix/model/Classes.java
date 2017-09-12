package com.brubix.model;

import com.brubix.model.enums.Subject;

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
@Table(name = "classes", catalog = "bigrubix")
public class Classes {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    Long id;

    private List<Subject> subjects;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    private List<Students> students;
}
