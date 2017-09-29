package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SchoolData {

    private String name;

    private String code;

    private List<AddressData> addresses;

    private byte[] logo;
}
