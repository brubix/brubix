package com.brubix.entity.inventory;

import com.brubix.entity.reference.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "course_subject",
            joinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"))
    private List<Subject> subjects;

    @ManyToMany(mappedBy = "courses")
    private List<School> schools;
}
