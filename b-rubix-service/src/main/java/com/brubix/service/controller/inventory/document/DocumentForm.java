package com.brubix.service.controller.inventory.document;

import com.brubix.service.controller.inventory.DocumentData;
import com.brubix.service.controller.inventory.document.constraint.BiFieldMatch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@BiFieldMatch.List({
        @BiFieldMatch(first = "documents", second = "attachments", message = "{invalid.document.attachments}")})
public class DocumentForm {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<DocumentData> documents;

    @JsonIgnore
    private List<MultipartFile> attachments;

    @JsonIgnore
    private String uid;

}
