package com.brubix.entity.inventory;

import com.brubix.entity.identity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */


@Getter
@Setter
@Entity
@Table(name = "teacher", catalog = "brubix")
@DiscriminatorValue("teacher")
public class Teacher extends User {

    @Column(name = "joining_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @Column(name = "resignation_date")
    @Temporal(TemporalType.DATE)
    private Date resignationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private School school;

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

}
