package com.brubix.service.controller.inventory.school;

import com.brubix.service.controller.inventory.AddressData;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

/**
 * Created by Sanjaya on 26/01/18.
 */
@Getter
@Setter
public class SchoolInfoData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 250, message = "{invalid.length.school.name}")
    private String name;

    @Length(max = 1000, message = "{invalid.length.school.about}")
    private String about;

    @Valid
    private AddressData address;

    @Valid
    private KnowYourSchoolData kys;

}
