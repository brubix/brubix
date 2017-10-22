package com.brubix.entity.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "NonFaculty", catalog = "brubix")
@DiscriminatorValue("NonFaculty")
public class NonFaculty extends Staff {
}
