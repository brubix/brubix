package com.brubix.entity.content;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 14/09/17.
 */
@Entity
@Table(name = "document", catalog = "brubix")
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "content")
    private byte[] content;

    @Column(name = "mime_type")
    private String mimeType;
}
