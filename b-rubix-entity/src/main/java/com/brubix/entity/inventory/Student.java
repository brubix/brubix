package com.brubix.entity.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "student", catalog = "brubix")
@DiscriminatorValue("student")
@Getter
@Setter
public class Student extends Person {


    @Temporal(TemporalType.DATE)
    @Column(name = "admission_date", nullable = false)
    private Date dateOfAdmission;

    @Temporal(TemporalType.DATE)
    @Column(name = "passout_date")
    private Date dateOfPassout;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parent parent;
}
