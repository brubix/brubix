package com.brubix.entity.reference;

import com.brubix.entity.inventory.Course;
import com.brubix.entity.inventory.Faculty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sanjaya on 15/09/17.
 */

@Entity
@Table(name = "subject", catalog = "brubix")
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "subjects")
    private List<Course> courses;

    @ManyToMany
    @JoinTable(
            name = "subjects_faculties",
            joinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "faculty_id", referencedColumnName = "id"))
    private List<Faculty> faculties;

}
