package com.brubix.entity.inventory;

import com.brubix.entity.communication.Social;
import com.brubix.entity.reference.InstitutionAffiliation;
import com.brubix.entity.reference.InstitutionType;
import com.brubix.entity.reference.Language;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Faculty> faculties;

    @OneToMany(cascade = CascadeType.ALL)
    private List<NonFaculty> nonFaculties;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private List<DocumentInfo> documents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private Social social;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_type_id")
    private List<InstitutionType> institutionTypes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliation_type_id")
    private List<InstitutionAffiliation> affiliations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private List<Language> languages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "school_course",
            joinColumns = @JoinColumn(
                    name = "school_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;


    @Column(name = "about", length = 1000)
    private String about;

    @Embedded
    private MileStone mileStone;

}