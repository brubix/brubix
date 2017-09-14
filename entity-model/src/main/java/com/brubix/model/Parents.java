package com.brubix.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "parents", catalog = "bigrubix")
public class Parents extends Person {

    private List<Students> wards;
}
