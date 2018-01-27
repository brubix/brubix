package feature.stepdef;

import com.brubix.common.exception.error.ErrorResponse;
import com.brubix.reference.controller.subject.SubjectForm;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import org.assertj.core.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public class SubjectStepDef extends AbstractStepDef {

    private List<SubjectForm.SubjectData> subjectDataList;
    private ResponseEntity<String> responseEntity;


    @Given("^the below subjects available$")
    public void belowSubjectsAvailable(List<SubjectForm.SubjectData> subjectDataList) {
        this.subjectDataList = subjectDataList;
    }

    @When("^the administrator want to create subjects$")
    public void systemAdministratorWantToCreateSubjects() throws Exception {
        String countryEndPoint = "http://localhost:" + serverPort + contextPath + "/subjects";
        SubjectForm subjectForm = new SubjectForm();
        subjectForm.setSubjects(subjectDataList);

        HttpEntity<SubjectForm> httpEntity = new HttpEntity<>(subjectForm, buildHeaders());
        responseEntity = restTemplate.postForEntity(new URI(countryEndPoint), httpEntity, String.class);
    }

    @Then("the administrator should get below subjects")
    public void systemAdministratorShouldGetBelowSubjects(List<SubjectForm.SubjectData> subjectDataList) throws Exception {
        String countryEndPoint = "http://localhost:" + serverPort + contextPath + "/subjects";

        responseEntity = restTemplate.getForEntity(new URI(countryEndPoint), String.class);
        JSONAssert.assertEquals(gson.toJson(subjectDataList), responseEntity.getBody(), true);
    }

    @Then("^the administrator should get error message as \"([^\"]*)\"$")
    public void theUserShouldGetError(String errorMessage) {
        Assertions.assertThat(responseEntity.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        String response = responseEntity.getBody();
        ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(errorMessage);
        Assertions.assertThat(errorResponse.getFieldErrors().size()).isGreaterThan(0);
    }

}
