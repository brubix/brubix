package com.brubix.entity.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "parent", catalog = "brubix")
@DiscriminatorValue("parent")
@Getter
@Setter
public class Parent extends Person {

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Student> wards;
}
