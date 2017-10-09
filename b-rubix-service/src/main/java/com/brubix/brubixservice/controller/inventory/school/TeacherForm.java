package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.KYCData;
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

    private List<KYCData> kyc;

    private Date joiningDate;

    private List<String> phones;

    private List<String> emails;
}
