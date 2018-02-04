package feature.inventory;

import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.service.controller.inventory.AddressData;
import com.brubix.service.controller.inventory.SocialData;
import com.brubix.service.controller.inventory.school.AdminInfoData;
import com.brubix.service.controller.inventory.school.InstitutionCreateRequest;
import com.brubix.service.controller.inventory.school.SchoolInfoData;
import com.brubix.service.controller.inventory.school.InstitutionQueryData;
import com.brubix.service.service.institution.InstitutionCode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import feature.SharedDataContext;
import org.assertj.core.api.Assertions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchoolStepDef extends AbstractStepDef {

    private InstitutionCreateRequest schoolForm;
    private ResponseEntity<String> responseEntity;
    private List<String> attachmentNames = new ArrayList<>();
    private String logo;
    private String schoolCode;
    private ResponseEntity<InstitutionQueryData> schoolDataResponseEntity;

    @Given("^the user provided school name - \"([^\"]*)\" , school id - \"([^\"]*)\" and below addresses$")
    public void theUserProvidedSchoolNameAsAndBelowAddresses(String name, String userName, List<AddressData> addressDataList) {

        SchoolInfoData data = new SchoolInfoData();
        data.setName(name);
        data.setAddress(addressDataList.get(0));

        AdminInfoData adminInfoData = new AdminInfoData();
        adminInfoData.setFirstName(userName);

        InstitutionCreateRequest schoolForm = new InstitutionCreateRequest();
        schoolForm.setSchoolInfo(data);
        schoolForm.setAdminInfos(Arrays.asList(adminInfoData));

        this.schoolForm = schoolForm;
    }

    @When("^the user creates school$")
    public void theUserCreatesSchool() throws Exception {

        // institution json data
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("institution", schoolForm);

        // institution logo
        if (logo != null) {
            FileSystemResource logoResource = new FileSystemResource(this.getClass()
                    .getClassLoader().getResource(logo).getPath());
            parts.add("profile", logoResource);
        }

        // institution DocumentInfo documents
        for (String attachment : attachmentNames) {
            FileSystemResource resource = new FileSystemResource(this.getClass()
                    .getClassLoader().getResource("doc/" + attachment).getPath());
            parts.add("attachments", resource);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity(parts, headers);

        String url = "http://localhost:" + serverPort + contextPath + "/schools";
        responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        SharedDataContext.setResponseEntity(responseEntity);
    }

    @Then("^a school code is generated$")
    public void aSchoolCodeIsGenerated() throws Exception {
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        InstitutionCode schoolCode = gson.fromJson(responseEntity.getBody(), InstitutionCode.class);
        Assertions.assertThat(schoolCode.getName()).isEqualTo(schoolForm.getSchoolInfo().getName());
        Assertions.assertThat(schoolCode.getCode()).contains("SCHL");
        this.schoolCode = schoolCode.getCode();
        // set institution code in shared data context
        SharedDataContext.setSchoolCode(schoolCode.getCode());
    }

    @When("^user finds school detail by school code$")
    public void userFindsSchoolDetailBySchoolCode() {
        String url = "http://localhost:" + serverPort + contextPath + "/schools/" + schoolCode;
        HttpEntity requestEntity = new HttpEntity(buildHeaders());
        schoolDataResponseEntity = exchange(url, HttpMethod.GET, requestEntity, InstitutionQueryData.class);
    }

    @Then("^below address data should be present for school \"([^\"]*)\" without logo$")
    public void belowDataShouldBePresent(String schoolName, List<AddressData> addressDataList) {

       /* List<AddressData> list = addressDataList.stream()
                .map(addressData -> {
                    return AddressData
                            .builder()
                            .stateCode(isNotEmpty(addressData.getState())
                                    ? addressData.getState()
                                    : null)
                            .countryCode(isNotEmpty(addressData.getCountry())
                                    ? addressData.getCountry()
                                    : null)
                            .pinCode(isNotEmpty(addressData.getPin())
                                    ? addressData.getPin()
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

        InstitutionQueryData schoolQueryData = schoolDataResponseEntity.getBody();
        Assertions.assertThat(schoolQueryData.getName()).isEqualTo(schoolName);
        Assertions.assertThat(schoolQueryData.getLogo()).isNull();
        Assertions.assertThat(schoolQueryData.getSocial()).isNull();

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
                            .stateCode(isNotEmpty(addressData.getState())
                                    ? addressData.getState()
                                    : null)
                            .countryCode(isNotEmpty(addressData.getCountry())
                                    ? addressData.getCountry()
                                    : null)
                            .pinCode(isNotEmpty(addressData.getPin())
                                    ? addressData.getPin()
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

        InstitutionQueryData schoolQueryData = schoolDataResponseEntity.getBody();
        Assertions.assertThat(schoolQueryData.getName()).isEqualTo(schoolName);
        Assertions.assertThat(schoolQueryData.getLogo()).isNotNull();

        /*Assertions.assertThat(schoolData.getAddresses())
                .hasSize(2)
                .containsAll(addressDataList);*/
    }

    @Then("^below social details should be present$")
    public void belowSocialDetailsShouldBePresent(List<SocialData> socialData) {
        SocialData social = schoolDataResponseEntity.getBody().getSocial();
        Assertions.assertThat(social.getFacebook()).isEqualTo(socialData.get(0).getFacebook());
        Assertions.assertThat(social.getGooglePlus()).isEqualTo(socialData.get(0).getGooglePlus());
        Assertions.assertThat(social.getLinkedin()).isEqualTo(socialData.get(0).getLinkedin());
        Assertions.assertThat(social.getTwitter()).isEqualTo(socialData.get(0).getTwitter());
    }

    @Then("^the user should get error as \"([^\"]*)\"$")
    public void theUserShouldGetError(String errorMessage) {
        ResponseEntity<String> responseEntity = SharedDataContext.getResponseEntity();
        Assertions.assertThat(responseEntity.getStatusCode()
                .value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        String response = responseEntity.getBody();
        ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(errorMessage);
    }

    @And("^the user has provided below social details$")
    public void theUserHasSocialDetails(List<SocialData> socialData) {
        schoolForm.setSocial(socialData.get(0));
    }
}
