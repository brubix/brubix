package com.brubix.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sanjaya on 15/09/17.
 */

@Entity
@Table(name = "course", catalog = "brubix")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "course")
    private List<Subject> subjects;

    @Column(name = "description")
    private String description;
}
