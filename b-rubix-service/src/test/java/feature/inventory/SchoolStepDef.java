package feature.inventory;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.service.inventory.school.SchoolCode;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import org.assertj.core.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class SchoolStepDef extends AbstractStepDef {

    private SchoolForm schoolForm;
    private ResponseEntity<SchoolCode> schoolCodeResponseEntity;

    @Given("^the user provided school name as \"([^\"]*)\" and below addresses$")
    public void theUserProvidedSchoolNameAsAndBelowAddresses(String name, List<AddressData> addressDataList) {
        SchoolForm schoolForm = new SchoolForm();
        schoolForm.setName(name);
        schoolForm.setAddresses(addressDataList);
        this.schoolForm = schoolForm;
    }

    @When("^the user creates school$")
    public void theUserCreatesSchool() throws Exception {

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("school", schoolForm);

        String url = "http://localhost:" + serverPort + contextPath + "/schools";
        schoolCodeResponseEntity = restTemplate.postForEntity(url, parts, SchoolCode.class);
    }

    @Then("^a school code is generated$")
    public void aSchoolCodeIsGenerated() throws Exception {
        Assertions.assertThat(schoolCodeResponseEntity.getStatusCode().value()).isEqualTo(200);
        Assertions.assertThat(schoolCodeResponseEntity.getBody().getName()).isEqualTo(schoolForm.getName());
        Assertions.assertThat(schoolCodeResponseEntity.getBody().getCode()).contains("SCHL");
    }
}
