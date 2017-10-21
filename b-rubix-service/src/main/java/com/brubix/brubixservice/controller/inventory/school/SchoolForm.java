package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.DocumentData;
import com.brubix.brubixservice.controller.inventory.SocialData;
import com.brubix.brubixservice.controller.inventory.document.constraint.BiFieldMatch;
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
// FIXME - once school form validation decided
/*@BiFieldMatch.List({
        @BiFieldMatch(first = "documents", second = "kycDocuments", message = "{invalid.documents.attachments}")})*/
public class SchoolForm {

    @NotBlank(message = "{field.empty}")
    @Length(max = 250, message = "{invalid.length.school.name}")
    private String name;

    @NotBlank(message = "{field.empty}")
    @Length(min = 6, max = 10, message = "{invalid.length.school.userName}")
    private String userName;

    @Valid
    @NotEmpty(message = "{field.empty}")
    private List<AddressData> addresses;

    @Valid
    private List<DocumentData> kyc;

    @Valid
    private SocialData social;

    @JsonIgnore
    private MultipartFile schoolLogo;

    @JsonIgnore
    private List<MultipartFile> kycDocuments;



}
