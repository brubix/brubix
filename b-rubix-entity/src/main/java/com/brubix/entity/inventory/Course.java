package com.brubix.entity.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Sanjaya on 15/09/17.
 */

@Entity
@Table(name = "course", catalog = "brubix")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @Column(name = "description")
    private String description;
}
