package com.brubix.entity.inventory;

import com.brubix.entity.reference.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */


@Getter
@Setter
@Entity
@Table(name = "faculty", catalog = "brubix")
@DiscriminatorValue("faculty")
public class Faculty extends Staff {

    @ManyToMany(mappedBy = "faculties")
    private List<Subject> subjects;

}
