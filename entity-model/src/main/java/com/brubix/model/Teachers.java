package com.brubix.model;

import com.brubix.model.enums.Subject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "teachers", catalog = "bigrubix")
public class Teachers extends Person {

    @Column(name = "joining_date", nullable = false)
    private Date joiningDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "resignation_date", nullable = false)
    private Date resignationDate;

    private List<Subject> subjectExperts;

    private List<Classes> classes;
}
