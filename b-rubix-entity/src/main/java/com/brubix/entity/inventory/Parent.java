package com.brubix.entity.inventory;

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
@Table(name = "parent", catalog = "brubix")
@Getter
@Setter
public class Parent extends Person {

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Student> wards;
}
