package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.SocialData;
import com.brubix.brubixservice.controller.inventory.school.CourseForm;
import com.brubix.brubixservice.controller.inventory.school.SchoolQueryData;
import com.brubix.brubixservice.controller.reference.subject.SubjectForm;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.entity.communication.Social;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.Course;
import com.brubix.entity.inventory.School;
import com.brubix.entity.reference.Subject;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.brubix.brubixservice.exception.error.ErrorCode.INVALID_SCHOOL_CODE;

@Slf4j
public class SchoolQueryHandlerImpl implements SchoolQueryHandler {

    private SchoolRepository schoolRepository;

    public SchoolQueryHandlerImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    @Transactional
    public SchoolQueryData findSchoolByCode(String code) {
        School school = schoolRepository.findBySchoolCode(code);

        if (school == null) {
            log.info("School code provided as {} is not found in system", code);
            throw new BrubixException(INVALID_SCHOOL_CODE);
        }

        return SchoolQueryData
                .builder()
                .addresses(mapToAddress(school.getAddresses()))
                .social(mapSocial(school.getSocial()))
                .code(school.getSchoolCode())
                .name(school.getSchoolName())
                .logo(school.getLogo() != null ? school.getLogo().getContent() : null)
                .build();
    }

    private SocialData mapSocial(Social social) {
        return social != null ?
                SocialData.builder()
                        .faceBook(social.getFaceBook())
                        .googlePlus(social.getGPlus())
                        .linkedIn(social.getLinkedIn())
                        .twitter(social.getTwitter())
                        .build()
                : null;
    }


    @Override
    @Transactional
    public List<CourseForm.CourseData> findAllCoursesBySchoolCode(String code) {
        School school = schoolRepository.findBySchoolCode(code);
        if (school == null) {
            log.info("School code provided as {} is not found in system", code);
            throw new BrubixException(INVALID_SCHOOL_CODE);
        }

        return school.getCourses()
                .stream()
                .map(course -> mapToCourse(course))
                .collect(Collectors.toList());

    }

    private CourseForm.CourseData mapToCourse(Course course) {
        CourseForm.CourseData courseData = new CourseForm.CourseData();
        courseData.setName(course.getName());
        courseData.setDescription(course.getDescription());
        courseData.setSubjects(mapToSubject(course.getSubjects()));
        return courseData;
    }

    private List<SubjectForm.SubjectData> mapToSubject(List<Subject> subjects) {
        return subjects
                .stream()
                .map(subject -> {
                            SubjectForm.SubjectData subjectData = new SubjectForm.SubjectData();
                            subjectData.setName(subject.getName());
                            subjectData.setDescription(subject.getDescription());
                            return subjectData;
                        }
                ).collect(Collectors.toList());

    }

    private List<AddressData> mapToAddress(List<Address> addresses) {
        return addresses
                .stream()
                .map(address -> {
                    return AddressData
                            .builder()
                            .firstLine(address.getFirstLine())
                            .secondLine(address.getSecondLine())
                            .thirdLine(address.getThirdLine())
                            .pinCode(address.getPinCode())
                            .countryCode(address.getCountry().getCode())
                            .stateCode(address.getState().getCode())
                            .build();

                }).collect(Collectors.toList());
    }
}
