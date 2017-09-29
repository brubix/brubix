package com.brubix.entity.inventory;


import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "address", catalog = "brubix")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_line", nullable = false, length = 250)
    private String firstLine;

    @Column(name = "second_line", length = 250)
    private String secondLine;

    @Column(name = "third_line", length = 250)
    private String thirdLine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    @Column(name = "pin_code", length = 6, nullable = false)
    private String pinCode;
}
