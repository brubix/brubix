package com.brubix.service.controller.inventory.school;

import com.brubix.service.controller.inventory.AddressData;
import com.brubix.service.controller.inventory.SocialData;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SchoolQueryData {

    private String name;

    private String code;

    private AddressData address;

    private SocialData social;

    private byte[] logo;
}
