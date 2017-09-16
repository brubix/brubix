package com.brubix.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "parents", catalog = "brubix")
@Getter
@Setter
public class Parents extends Person {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student> wards;
}
