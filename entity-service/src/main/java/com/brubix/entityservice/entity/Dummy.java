package com.brubix.entityservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DUMMY")
@Getter
@Setter
@javax.persistence.TableGenerator(
        name = "SEQ_GEN",
        table = "GENERATOR_TABLE",
        pkColumnName = "KEY",
        valueColumnName = "VALUE",
        pkColumnValue = "SEQ",
        allocationSize = 20
)
public class Dummy {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_GEN")
    @Column(name = "DUM_ID")
    @JsonIgnore
    private Long id;

    @Column(name = "DATA")
    private String data;
}
