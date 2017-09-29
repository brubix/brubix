package feature.inventory;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.KYCData;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.service.inventory.school.SchoolCode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolStepDef extends AbstractStepDef {

    private SchoolForm schoolForm;
    private ResponseEntity<SchoolCode> schoolCodeResponseEntity;
    private List<String> attachmentNames = new ArrayList<>();
    private String logo;

    @Given("^the user provided school name as \"([^\"]*)\" and below addresses$")
    public void theUserProvidedSchoolNameAsAndBelowAddresses(String name, List<AddressData> addressDataList) {
        SchoolForm schoolForm = new SchoolForm();
        schoolForm.setName(name);
        schoolForm.setAddresses(addressDataList);
        this.schoolForm = schoolForm;
    }

    @When("^the user creates school$")
    public void theUserCreatesSchool() throws Exception {

        // school json data
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("school", schoolForm);

        // school logo
        if (logo != null) {
            FileSystemResource logoResource = new FileSystemResource(this.getClass()
                    .getClassLoader().getResource(logo).getPath());
            parts.add("LOGO", logoResource);
        }

        // school KYC documents
        for (String attachment : attachmentNames) {
            FileSystemResource resource = new FileSystemResource(this.getClass()
                    .getClassLoader().getResource("doc/" + attachment).getPath());
            parts.add("KYC", resource);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity(parts, headers);

        String url = "http://localhost:" + serverPort + contextPath + "/schools";
        schoolCodeResponseEntity =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, SchoolCode.class);

    }

    @And("^the user has provided below kyc$")
    public void theUserHasBelowKyc(List<TestKYCData> testKYCData) {
        schoolForm.setKyc(testKYCData.
                stream()
                .map(kyc -> {
                    KYCData kycData = new KYCData();
                    kycData.setNumber(kyc.getNumber());
                    kycData.setType(kyc.getType());
                    if (StringUtils.isNotBlank(kyc.getDocument())) {
                        attachmentNames.add(kyc.getDocument());
                    }
                    return kycData;
                }).collect(Collectors.toList()));
    }

    @And("logo \"([^\"]*)\" provided")
    public void logo_attached(String logo) {
        this.logo = "doc/" + logo;
    }

    @Then("^a school code is generated$")
    public void aSchoolCodeIsGenerated() throws Exception {
        Assertions.assertThat(schoolCodeResponseEntity.getStatusCode().value()).isEqualTo(200);
        Assertions.assertThat(schoolCodeResponseEntity.getBody().getName()).isEqualTo(schoolForm.getName());
        Assertions.assertThat(schoolCodeResponseEntity.getBody().getCode()).contains("SCHL");
    }
}
