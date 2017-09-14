package com.brubix.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Entity
@Table(name = "kyc", catalog = "bigrubix")
public class KYC {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "adhar_number", length = 12)
    private String adhaarNumber;

    @Column(name = "pan_number", length = 10)
    private String panCard;

    @Column(name = "driving_license_number", length = 20)
    private String drivingLicenseNumber;
}
