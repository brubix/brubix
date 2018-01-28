package com.brubix.service.controller.inventory.school;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Sanjaya on 26/01/18.
 */
@Getter
@Setter
public class AdminInfoData {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
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
    @Email(regexp = EMAIL_PATTERN, message ="{invalid.email}")
    private String email;
}
