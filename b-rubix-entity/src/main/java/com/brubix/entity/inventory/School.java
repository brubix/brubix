package com.brubix.entity.inventory;

import com.brubix.entity.content.Document;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 14/09/17.
 */
@Entity
@Table(name = "school", catalog = "brubix")
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "school_name", length = 250, nullable = false)
    private String schoolName;

    @Column(name = "school_code", length = 25, nullable = false)
    private String schoolCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", nullable = false)
    private List<Address> addresses;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Teacher> teachers;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Document schoolLogo;

    @Embedded
    private MileStone mileStone;

}
