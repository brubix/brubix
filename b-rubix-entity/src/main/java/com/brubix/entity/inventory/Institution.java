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
@Table(name = "institution", catalog = "brubix")
@Getter
@Setter
public class Institution {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "institution_name", length = 250, nullable = false)
    private String institutionName;

    @Column(name = "institution_code", length = 25, nullable = false, unique = true)
    private String institutionCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Faculty> faculties;

    @OneToMany(cascade = CascadeType.ALL)
    private List<NonFaculty> nonFaculties;

    // TODO verify this association
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private List<DocumentInfo> documents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private Social social;

    @OneToMany(fetch = FetchType.LAZY)
    private List<InstitutionType> institutionTypes;

    @OneToMany(fetch = FetchType.LAZY)
    private List<InstitutionAffiliation> affiliations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Language> languages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "institution_course",
            joinColumns = @JoinColumn(
                    name = "institution_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;


    @Column(name = "about", length = 1000)
    private String about;

    @Embedded
    private MileStone mileStone;

}