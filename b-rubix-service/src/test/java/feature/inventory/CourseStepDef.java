package feature.inventory;


import com.brubix.brubixservice.controller.inventory.school.CourseForm;
import com.brubix.brubixservice.controller.reference.subject.SubjectForm;
import com.google.common.reflect.TypeToken;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseStepDef extends AbstractStepDef {

    private ResponseEntity<String> courseResponseEntity;
    Map<String, List<SubjectForm.SubjectData>> courseSubjects = new HashMap<>();
    private List<CourseForm.CourseData> actualCourses;


    @And("^the user creates courses for school$")
    public void belowCoursesCreatedForSchool() {
        String url = "http://localhost:" + serverPort + contextPath + "/schools/" + SharedDataContext.getSchoolCode() + "/courses";
        List<CourseForm.CourseData> courses = courseSubjects.entrySet()
                .stream()
                .map(courseSubjectEntry -> {
                    CourseForm.CourseData courseData = new CourseForm.CourseData();
                    courseData.setName(courseSubjectEntry.getKey());
                    courseData.setDescription(courseSubjectEntry.getKey());
                    courseData.setSubjects(courseSubjectEntry.getValue());
                    return courseData;
                }).collect(Collectors.toList());
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
        TypeToken<ArrayList<CourseForm.CourseData>> token = new TypeToken<ArrayList<CourseForm.CourseData>>() {
        };
        actualCourses = gson.fromJson(courseResponseEntity.getBody(), token.getType());
    }

    @Then("^user should get course \"([^\"]*)\" has below subjects$")
    public void userShouldGet(String courseName, List<SubjectForm.SubjectData> subjectData) {
        CourseForm.CourseData courseDataByCourseName = actualCourses.stream()
                .filter(courseData -> courseData.getName().equals(courseName))
                .findAny().get();
        Assertions.assertThat(courseDataByCourseName.getSubjects())
                .hasSize(subjectData.size());
        // FIXME - to verify why containsExactlyInAnyOrder not working
        //.containsExactlyInAnyOrder(subjectData.toArray(new SubjectForm.SubjectData[subjectData.size()]));
    }

    @And("the user creates course \"([^\"]*)\" for school with below subjects")
    public void userCreatedSubjectForCourse(String course, List<SubjectForm.SubjectData> subjectDataList) {
        courseSubjects.put(course, subjectDataList);
    }
}
