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
import com.brubix.service.controller.inventory.school.InstitutionQueryData;
import com.brubix.service.controller.inventory.school.SubjectForm;
import com.brubix.service.repository.inventory.InstitutionRepository;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SchoolQueryHandlerImpl implements InstitutionQueryHandler {

    private InstitutionRepository institutionRepository;

    public SchoolQueryHandlerImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    @Transactional
    public InstitutionQueryData findInstitutionByCode(String code) {
        Institution school = institutionRepository.findByInstitutionCode(code);

        if (school == null) {
            log.info("Institution code provided as {} is not found in system", code);
            throw new BrubixException(ErrorCode.INVALID_INSTITUTION_CODE);
        }

        Optional<DocumentInfo> profileOptional = school.getDocuments()
                .stream()
                .filter(documentInfo -> documentInfo.getDocumentType() == DocumentType.PROFILE)
                .findAny();
        Document profile = profileOptional.isPresent() ? profileOptional.get().getDocument() : null;

        return InstitutionQueryData
                .builder()
                .address(mapToAddress(school.getAddress()))
                .social(mapSocial(school.getSocial()))
                .code(school.getInstitutionCode())
                .name(school.getInstitutionName())
                .logo(profile != null ? profile.getContent() : null)
                .build();
    }


    private SocialData mapSocial(Social social) {
        return social != null ?
                SocialData.builder()
                        .facebook(social.getFaceBook())
                        .googlePlus(social.getGPlus())
                        .linkedin(social.getLinkedin())
                        .twitter(social.getTwitter())
                        .build()
                : null;
    }


    @Override
    @Transactional
    public List<CourseForm.CourseData> findAllCoursesBySchoolCode(String code) {
        Institution school = institutionRepository.findByInstitutionCode(code);
        if (school == null) {
            log.info("Institution code provided as {} is not found in system", code);
            throw new BrubixException(ErrorCode.INVALID_INSTITUTION_CODE);
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
