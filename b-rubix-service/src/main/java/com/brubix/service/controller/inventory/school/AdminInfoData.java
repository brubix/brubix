package com.brubix.service.controller.inventory.school;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Sanjaya on 26/01/18.
 */
@Getter
@Setter
public class AdminInfoData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 100, message = "{invalid.length.first.name}")
    private String firstName;

    @NotBlank(message = "{field.empty}")
    @Length(max = 100, message = "{invalid.length.last.name}")
    private String lastName;

    @NotBlank(message = "{field.empty}")
    @Length(max = 10, message = "{invalid.length.phone}")
    private String phone;

    @NotBlank(message = "{field.empty}")
    @Length(max = 255, message = "{invalid.length.email}")
    private String email;
}
