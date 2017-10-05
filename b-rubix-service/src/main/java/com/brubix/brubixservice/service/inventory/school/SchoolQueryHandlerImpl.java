package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.school.CourseQueryData;
import com.brubix.brubixservice.controller.inventory.school.SchoolQueryData;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.Course;
import com.brubix.entity.inventory.School;
import com.brubix.entity.inventory.Subject;
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
                .code(school.getSchoolCode())
                .name(school.getSchoolName())
                .logo(school.getLogo() != null ? school.getLogo().getContent() : null)
                .build();
    }


    @Override
    @Transactional
    public List<CourseQueryData> findAllCoursesBySchoolCode(String code) {
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

    private CourseQueryData mapToCourse(Course course) {
        return CourseQueryData
                .builder()
                .name(course.getName())
                .description(course.getDescription())
                .subjects(mapToSubject(course.getSubjects()))
                .build();
    }

    private List<CourseQueryData.SubjectQueryData> mapToSubject(List<Subject> subjects) {
        return subjects
                .stream()
                .map(subject -> {
                            return CourseQueryData
                                    .SubjectQueryData
                                    .builder()
                                    .name(subject.getName())
                                    .description(subject.getDescription())
                                    .build();
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
