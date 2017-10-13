package com.brubix.entity.communication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "social", catalog = "brubix")
@Getter
@Setter
public class Social {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "facebook", unique = true)
    private String faceBook;

    @Column(name = "twitter", unique = true)
    private String twitter;

    @Column(name = "gplus", unique = true)
    private String gPlus;

    @Column(name = "linkedin", unique = true)
    private String linkedIn;

}
