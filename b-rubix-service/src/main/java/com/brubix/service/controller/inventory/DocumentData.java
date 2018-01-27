package com.brubix.service.controller.inventory;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class DocumentData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 50, message = "{invalid.document.type.length}")
    private String type;

    @Length(max = 20, message = "{invalid.length.document.number}")
    private String number;
}
