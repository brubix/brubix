package com.brubix.service.service.school;

import com.brubix.common.exception.BrubixException;
import com.brubix.common.exception.error.ErrorCode;
import com.brubix.entity.communication.Social;
import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.Subject;
import com.brubix.service.controller.inventory.AddressData;
import com.brubix.service.controller.inventory.SocialData;
import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.SchoolQueryData;
import com.brubix.service.controller.inventory.school.SubjectForm;
import com.brubix.service.repository.inventory.SchoolRepository;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new BrubixException(ErrorCode.INVALID_SCHOOL_CODE);
        }

        Optional<DocumentInfo> profileOptional = school.getDocuments()
                .stream()
                .filter(documentInfo -> documentInfo.getDocumentType() == DocumentType.PROFILE)
                .findAny();
        Document profile = profileOptional.isPresent() ? profileOptional.get().getDocument() : null;

        return SchoolQueryData
                .builder()
                .address(mapToAddress(school.getAddress()))
                .social(mapSocial(school.getSocial()))
                .code(school.getSchoolCode())
                .name(school.getSchoolName())
                .logo(profile != null ? profile.getContent() : null)
                .build();
    }


    private SocialData mapSocial(Social social) {
        return social != null ?
                SocialData.builder()
                        .facebook(social.getFaceBook())
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
            throw new BrubixException(ErrorCode.INVALID_SCHOOL_CODE);
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

    private AddressData mapToAddress(Address address) {
        return AddressData
                .builder()
                .firstLine(address.getFirstLine())
                .secondLine(address.getSecondLine())
                .thirdLine(address.getThirdLine())
                .pin(address.getPinCode())
                .country(address.getCountry().getCode())
                .state(address.getState().getCode())
                .build();

    }
}
