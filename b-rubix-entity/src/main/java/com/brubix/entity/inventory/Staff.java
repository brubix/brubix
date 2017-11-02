package com.brubix.entity.inventory;

import com.brubix.entity.identity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class Staff extends User {

    @Column(name = "joining_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date joiningDate;

    @Column(name = "resignation_date")
    @Temporal(TemporalType.DATE)
    private Date resignationDate;

}
