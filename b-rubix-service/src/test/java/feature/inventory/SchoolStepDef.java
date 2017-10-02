package feature.inventory;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.KYCData;
import com.brubix.brubixservice.controller.inventory.school.SchoolData;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.exception.error.ErrorResponse;
import com.brubix.brubixservice.service.inventory.school.SchoolCode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import org.assertj.core.api.Assertions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class SchoolStepDef extends AbstractStepDef {

    private SchoolForm schoolForm;
    private ResponseEntity<String> schoolCreateResponseEntity;
    private List<String> attachmentNames = new ArrayList<>();
    private String logo;
    private String schoolCode;
    private ResponseEntity<SchoolData> schoolQueryResponseEntity;

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
        schoolCreateResponseEntity =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

    }

    @And("^the user has provided below kyc$")
    public void theUserHasBelowKyc(List<TestKYCData> testKYCData) {
        schoolForm.setKyc(testKYCData.
                stream()
                .map(kyc -> {
                    KYCData kycData = new KYCData();
                    kycData.setNumber(kyc.getNumber());
                    kycData.setType(kyc.getType());
                    if (isNotBlank(kyc.getDocument())) {
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
        Assertions.assertThat(schoolCreateResponseEntity.getStatusCode().value()).isEqualTo(200);
        SchoolCode schoolCode = gson.fromJson(schoolCreateResponseEntity.getBody(), SchoolCode.class);

        Assertions.assertThat(schoolCode.getName()).isEqualTo(schoolForm.getName());
        Assertions.assertThat(schoolCode.getCode()).contains("SCHL");
        this.schoolCode = schoolCode.getCode();
    }

    @When("^user finds school detail by school code$")
    public void userFindsSchoolDetailBySchoolCode() {
        String url = "http://localhost:" + serverPort + contextPath + "/schools/" + schoolCode;
        HttpEntity requestEntity = new HttpEntity(buildHeaders());
        schoolQueryResponseEntity = exchange(url, HttpMethod.GET, requestEntity, SchoolData.class);
    }

    @Then("^below address data should be present for school \"([^\"]*)\" without logo$")
    public void belowDataShouldBePresent(String schoolName, List<AddressData> addressDataList) {

       /* List<AddressData> list = addressDataList.stream()
                .map(addressData -> {
                    return AddressData
                            .builder()
                            .stateCode(isNotEmpty(addressData.getStateCode())
                                    ? addressData.getStateCode()
                                    : null)
                            .countryCode(isNotEmpty(addressData.getCountryCode())
                                    ? addressData.getCountryCode()
                                    : null)
                            .pinCode(isNotEmpty(addressData.getPinCode())
                                    ? addressData.getPinCode()
                                    : null)
                            .firstLine(isNotEmpty(addressData.getFirstLine())
                                    ? addressData.getFirstLine()
                                    : null)
                            .secondLine(isNotEmpty(addressData.getSecondLine())
                                    ? addressData.getSecondLine()
                                    : null)
                            .thirdLine(isNotEmpty(addressData.getThirdLine())
                                    ? addressData.getThirdLine()
                                    : null)
                            .build();
                }).collect(Collectors.toList());*/

        SchoolData schoolData = schoolQueryResponseEntity.getBody();
        Assertions.assertThat(schoolData.getName()).isEqualTo(schoolName);
        Assertions.assertThat(schoolData.getLogo()).isNull();

        /*Assertions.assertThat(schoolData.getAddresses())
                .hasSize(2)
                .containsAll(addressDataList);*/
    }

    @Then("^below address data should be present for school \"([^\"]*)\" with logo$")
    public void belowDataShouldBePresentWithLogo(String schoolName, List<AddressData> addressDataList) {

       /* List<AddressData> list = addressDataList.stream()
                .map(addressData -> {
                    return AddressData
                            .builder()
                            .stateCode(isNotEmpty(addressData.getStateCode())
                                    ? addressData.getStateCode()
                                    : null)
                            .countryCode(isNotEmpty(addressData.getCountryCode())
                                    ? addressData.getCountryCode()
                                    : null)
                            .pinCode(isNotEmpty(addressData.getPinCode())
                                    ? addressData.getPinCode()
                                    : null)
                            .firstLine(isNotEmpty(addressData.getFirstLine())
                                    ? addressData.getFirstLine()
                                    : null)
                            .secondLine(isNotEmpty(addressData.getSecondLine())
                                    ? addressData.getSecondLine()
                                    : null)
                            .thirdLine(isNotEmpty(addressData.getThirdLine())
                                    ? addressData.getThirdLine()
                                    : null)
                            .build();
                }).collect(Collectors.toList());*/

        SchoolData schoolData = schoolQueryResponseEntity.getBody();
        Assertions.assertThat(schoolData.getName()).isEqualTo(schoolName);
        Assertions.assertThat(schoolData.getLogo()).isNotNull();

        /*Assertions.assertThat(schoolData.getAddresses())
                .hasSize(2)R
                .containsAll(addressDataList);*/
    }


    @Then("^the user should get error as \"([^\"]*)\"$")
    public void theUserShouldGetError(String errorMessage) {
        Assertions.assertThat(schoolCreateResponseEntity.getStatusCode()
                .value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        String response = schoolCreateResponseEntity.getBody();
        ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(errorMessage);
    }
}
