package com.brubix.model.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Getter
@Setter
@Entity
@Table(name = "teacher", catalog = "brubix")
@DiscriminatorValue("teacher")
public class Teacher extends Person {


    @Column(name = "joining_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @Column(name = "resignation_date")
    @Temporal(TemporalType.DATE)
    private Date resignationDate;
}
