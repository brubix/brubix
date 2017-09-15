package com.brubix.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "teachers", catalog = "bigrubix")
@DiscriminatorValue("teacher")
@Getter
@Setter
public class Teachers extends Person {


    @Column(name = "joining_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @Column(name = "resignation_date")
    @Temporal(TemporalType.DATE)
    private Date resignationDate;
}
