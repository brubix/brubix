package com.brubix.entity.inventory;

import com.brubix.entity.communication.Social;
import com.brubix.entity.reference.InstitutionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 14/09/17.
 */
@Entity
@Table(name = "school", catalog = "brubix")
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "school_name", length = 250, nullable = false)
    private String schoolName;

    @Column(name = "school_code", length = 25, nullable = false, unique = true)
    private String schoolCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private List<Address> addresses;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Faculty> faculties;


    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<NonFaculty> nonFaculties;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private List<DocumentInfo> documents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private Social social;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_type_id")
    private InstitutionType institutionType;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "school_course",
            joinColumns = @JoinColumn(
                    name = "school_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;

    @Embedded
    private MileStone mileStone;

}