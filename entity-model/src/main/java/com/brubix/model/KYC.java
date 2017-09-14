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
    @Column(name = "id", unique = true, nullable = false)
    Integer id;

    @Column(name = "adhar_number" , length = 12)
    String adharNumber;

    @Column(name = "pan_number" , length = 10)
    String panCard;

    @Column(name = "driving_license_number" , length = 20)
    String drivingLicenseNumber;

    private BankDetails bankDetails;
}
