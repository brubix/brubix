package com.brubix.entity.inventory;

import com.brubix.entity.content.Document;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */
@Getter
@Setter
@Entity
@Table(name = "kyc", catalog = "brubix")
public class KYC {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kyc_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private KYCType kycType;

    @Column(name = "kyc_number", length = 20, nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;
}
