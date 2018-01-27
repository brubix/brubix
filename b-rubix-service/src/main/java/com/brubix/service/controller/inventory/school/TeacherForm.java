package com.brubix.service.controller.inventory.school;

import com.brubix.service.controller.inventory.AddressData;
import com.brubix.service.controller.inventory.DocumentData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TeacherForm {

    @JsonIgnore
    private String schoolCode;

    private String name;

    private Date dateOfBirth;

    private List<AddressData> addresses;

    private List<DocumentData> kyc;

    private Date joiningDate;

    private List<String> phones;

    private List<String> emails;
}
