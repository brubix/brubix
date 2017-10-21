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
@Table(name = "document_info", catalog = "brubix")
public class DocumentInfo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "document_number", length = 20, nullable = true)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;

}
