package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.DocumentData;
import com.brubix.brubixservice.controller.inventory.SocialData;
import com.brubix.brubixservice.controller.inventory.school.CourseForm;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.controller.reference.subject.SubjectForm;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.generator.CodeGenerator;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.inventory.SubjectRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.communication.Social;
import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.Subject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SchoolCommandHandlerImpl implements SchoolCommandHandler {

    private SchoolRepository schoolRepository;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;

    private CodeGenerator schoolCodeGenerator;
    private SchoolFormCustomValidator schoolFormCustomValidator;
    private SubjectRepository subjectRepository;


    public SchoolCommandHandlerImpl(SchoolRepository schoolRepository,
                                    CountryRepository countryRepository,
                                    StateRepository stateRepository,
                                    CodeGenerator schoolCodeGenerator,
                                    SchoolFormCustomValidator schoolFormCustomValidator,
                                    SubjectRepository subjectRepository) {
        this.schoolRepository = schoolRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.schoolCodeGenerator = schoolCodeGenerator;
        this.schoolFormCustomValidator = schoolFormCustomValidator;
        this.subjectRepository = subjectRepository;
    }


    @Override
    public SchoolCode create(SchoolForm schoolForm) {

        schoolFormCustomValidator.doValidate(schoolForm);

        log.info("Creating of school started");
        School school = mapToEntity(schoolForm);
        try {
            School savedSchool = schoolRepository.save(school);
            log.info("Loading of schools ended");

            return SchoolCode
                    .builder()
                    .code(savedSchool.getSchoolCode())
                    .name(savedSchool.getSchoolName())
                    .build();

        } catch (DataAccessException ex) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(ex.getCause()));
            throw new BrubixException(ErrorCode.LOADING_ERROR);
        }
    }

    @Override
    @Transactional
    public void create(CourseForm courseForm) {
        School school = schoolRepository.findBySchoolCode(courseForm.getSchoolCode());
        if (school == null) {
            throw new BrubixException(ErrorCode.INVALID_SCHOOL_CODE);
        }
        List<Course> courses =
                courseForm
                        .getCourses()
                        .stream()
                        .map(courseData -> {
                            Course course = new Course();
                            course.setName(courseData.getName());
                            course.setDescription(courseData.getDescription());
                            course.setSubjects(mapToSubject(courseData.getSubjects()));
                            return course;
                        }).collect(Collectors.toList());
        school.setCourses(courses);
    }

    private List<Subject> mapToSubject(List<SubjectForm.SubjectData> subjectDataList) {
        return subjectDataList
                .stream()
                .map(subjectData -> {
                    Subject subject = subjectRepository.findByName(subjectData.getName());
                    if (subject == null) {
                        throw new BrubixException(ErrorCode.INVALID_SUBJECT_NAME);
                    }
                    return subject;
                }).collect(Collectors.toList());
    }

    @Override
    public School mapToEntity(SchoolForm schoolForm) {

        School school = new School();
        school.setSchoolName(schoolForm.getName());
        school.setSchoolCode(schoolCodeGenerator.generate());

        List<DocumentInfo> documentInfoList = new ArrayList<>();

        Document profileDocument = schoolForm.getProfilePicture() != null ? createDocument(schoolForm.getProfilePicture()) : null;
        if (profileDocument != null) {
            DocumentInfo profileDocumentInfo = new DocumentInfo();
            profileDocumentInfo.setDocumentType(DocumentType.PROFILE);
            profileDocumentInfo.setDocument(profileDocument);
            documentInfoList.add(profileDocumentInfo);
        }

        // map addresses
        List<Address> addresses = schoolForm.getAddresses()
                .stream()
                .map(addressData -> {
                    Address address = new Address();
                    address.setFirstLine(addressData.getFirstLine());
                    address.setSecondLine(addressData.getSecondLine());
                    address.setThirdLine(addressData.getThirdLine());
                    address.setPinCode(addressData.getPinCode());
                    address.setCountry(countryRepository.findByCode(addressData.getCountryCode()));
                    address.setState(stateRepository.findByCode(addressData.getStateCode()));
                    return address;
                }).collect(Collectors.toList());

        // map KYCs
        int size = CollectionUtils.isEmpty(schoolForm.getDocuments()) ? 0 : schoolForm.getDocuments().size();
        for (int i = 0; i < size; i++) {
            DocumentData documentData = schoolForm.getDocuments().get(i);

            DocumentInfo documentInfo = new DocumentInfo();
            documentInfo.setDocumentType(DocumentType.getType(documentData.getType()));
            documentInfo.setNumber(documentData.getNumber());

            if (!CollectionUtils.isEmpty(schoolForm.getAttachments())) {
                // FIXME - Nasty way need to re-factor
                try {
                    MultipartFile attachment = schoolForm.getAttachments().get(i);
                    documentInfo.setDocument(attachment != null ? createDocument(attachment) : null);
                } catch (ArrayIndexOutOfBoundsException e) {
                    log.info("Attachment not provided for document type" + documentData.getType());
                }
            }
            documentInfoList.add(documentInfo);
        }

        // map Milestones
        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        //FIXME - association with user entity and identity
        mileStone.setCreatedBy(1);

        school.setDocuments(documentInfoList);
        school.setAddresses(addresses);
        school.setMileStone(mileStone);

        //map to social data
        SocialData socialData = schoolForm.getSocial();
        if (anySocialLinkPresent(socialData)) {
            Social social = new Social();
            social.setFaceBook(socialData.getFaceBook());
            social.setGPlus(socialData.getGooglePlus());
            social.setLinkedIn(socialData.getLinkedIn());
            social.setTwitter(socialData.getTwitter());
            school.setSocial(social);
        }
        return school;
    }

    private boolean anySocialLinkPresent(SocialData socialData) {
        return socialData != null && (StringUtils.isNotBlank(socialData.getFaceBook())
                || StringUtils.isNotBlank(socialData.getGooglePlus())
                || StringUtils.isNotBlank(socialData.getLinkedIn())
                || StringUtils.isNotBlank(socialData.getTwitter()));
    }

    private Document createDocument(MultipartFile file) {
        try {
            Document document = new Document();
            document.setDocumentName(file.getName());
            document.setContent(file.getBytes());
            document.setMimeType(file.getContentType());
            return document;
        } catch (IOException exception) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(exception.getCause()));
            throw new BrubixException(ErrorCode.INVALID_FILE);
        }
    }
}
