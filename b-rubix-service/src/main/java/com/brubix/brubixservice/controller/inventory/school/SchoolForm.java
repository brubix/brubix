package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.MileStone;

import java.util.List;

public class SchoolForm {

    private String schoolName;

    private String schoolCode;

    private List<Address> addresses;

    private Document schoolLogo;
    private MileStone mileStone;
}
