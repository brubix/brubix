package com.brubix.model.inventory;

import com.brubix.model.content.Document;
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

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "school_code", nullable = false)
    private String schoolCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    private Document schoolLogo;

    @Embedded
    private MileStone mileStone;

}
