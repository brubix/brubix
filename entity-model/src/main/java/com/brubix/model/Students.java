package com.brubix.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "students", catalog = "bigrubix")
@DiscriminatorValue("student")
@Getter
@Setter
public class Students extends Person {


    @Temporal(TemporalType.DATE)
    @Column(name = "admission_date", nullable = false)
    private Date dateOfAdmission;

    @Temporal(TemporalType.DATE)
    @Column(name = "passout_date")
    private Date dateOfPassout;
}
