package feature.inventory;


import com.brubix.brubixservice.controller.inventory.school.CourseForm;
import com.brubix.brubixservice.controller.inventory.school.CourseQueryData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import feature.SharedDataContext;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CourseStepDef extends AbstractStepDef {

    private ResponseEntity<String> courseResponseEntity;


    @And("^the user creates below courses for school$")
    public void belowCoursesCreatedForSchool(List<CourseForm.CourseData> courses) {
        String url = "http://localhost:" + serverPort + contextPath + "/schools/" + SharedDataContext.getSchoolCode() + "/courses";

        CourseForm courseForm = new CourseForm();
        courseForm.setCourses(courses);

        HttpEntity requestEntity = new HttpEntity(courseForm, buildHeaders());
        courseResponseEntity =
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        assertThat(courseResponseEntity.getStatusCodeValue())
                .isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @When("^user finds all courses for school$")
    public void userFindsAllCoursesForSchool() {
        String url = "http://localhost:" + serverPort + contextPath + "/schools/" +
                SharedDataContext.getSchoolCode() + "/courses";

        HttpEntity requestEntity = new HttpEntity(buildHeaders());
        courseResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    @Then("^user should get$")
    public void userShouldGet(List<CourseForm.CourseData> courses) {
        List<CourseQueryData> actual = (List<CourseQueryData>) gson.fromJson(courseResponseEntity.getBody(), List.class);
        Assertions.assertThat(actual).hasSize(courses.size())
                .extracting("name", "description", "subjects")
                .contains(tuple("STD1", "standard 1", new ArrayList<>()),
                        tuple("STD2", "standard 2", new ArrayList<>()),
                        tuple("STD3", "standard 3", new ArrayList<>()),
                        tuple("STD4", "standard 4", new ArrayList<>()));

    }
}
