package feature.inventory;

import com.brubix.brubixservice.controller.inventory.DocumentData;
import com.brubix.brubixservice.controller.inventory.document.DocumentForm;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import feature.AbstractStepDef;
import feature.SharedDataContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.assertj.core.api.Assertions.assertThat;

public class DocumentStepDef extends AbstractStepDef {

    private List<String> attachments = new ArrayList<>();

    @And("^the user has uploaded below document$")
    public void theUserHasBelowKyc(List<TestDocumentData> testDocumentData) {
        DocumentForm documentForm = new DocumentForm();
        documentForm.setUid(SharedDataContext.getSchoolCode());
        documentForm.setDocuments(testDocumentData.
                stream()
                .map(documentData -> {
                    DocumentData kycData = new DocumentData();
                    kycData.setNumber(documentData.getNumber());
                    kycData.setType(documentData.getType());
                    if (isNotBlank(documentData.getDocument())) {
                        attachments.add(documentData.getDocument());
                    }
                    return kycData;
                }).collect(Collectors.toList()));

        // document details json data
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("documents", documentForm);

        // school DocumentInfo documents
        for (String attachment : attachments) {
            FileSystemResource resource = new FileSystemResource(this.getClass().getClassLoader().getResource("doc/" + attachment).getPath());
            parts.add("attachments", resource);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity(parts, headers);

        String url = "http://localhost:" + serverPort + contextPath + "/documents/" + SharedDataContext.getSchoolCode();
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        SharedDataContext.setResponseEntity(responseEntity);
    }

    @Then("^the response status should be \"([^\"]*)\"$")
    public void thenResponseShouldBeOk(String status) {
        ResponseEntity responseEntity = SharedDataContext.getResponseEntity();
        assertThat(HttpStatus.valueOf(status)).isEqualTo(responseEntity.getStatusCode());
    }
}
