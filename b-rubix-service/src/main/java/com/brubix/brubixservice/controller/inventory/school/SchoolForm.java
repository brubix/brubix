package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class SchoolForm {

    @NotBlank(message = "{field.empty}")
    @Length(max = 250, message = "{invalid.length.school.name}")
    private String name;

    @Valid
    @NotEmpty(message = "{field.empty}")
    private List<AddressData> addresses;

    @JsonIgnore
    private MultipartFile schoolLogoFile;

}
