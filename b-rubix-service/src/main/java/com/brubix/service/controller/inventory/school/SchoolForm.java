package com.brubix.service.controller.inventory.school;

import com.brubix.service.controller.inventory.SocialData;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class SchoolForm {

    @Valid
    private SchoolInfoData schoolInfo;

    @Valid
    @NotEmpty
    private List<AdminInfoData> adminInfos;

    @Valid
    private SocialData social;
}
