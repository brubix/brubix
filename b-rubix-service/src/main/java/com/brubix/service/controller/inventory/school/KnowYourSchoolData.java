package com.brubix.service.controller.inventory.school;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by Sanjaya on 26/01/18.
 */
@Getter
@Setter
public class KnowYourSchoolData {

    @NotEmpty(message = "{field.empty}")
    private List<String> institutionTypes;

    @NotEmpty(message = "{field.empty}")
    private List<String> affiliations;

    @NotEmpty(message = "{field.empty}")
    private List<String> languages;
}