package com.brubix.service.service.school;

import com.brubix.common.exception.BrubixException;
import com.brubix.common.exception.error.ErrorCode;
import com.brubix.common.repository.*;
import com.brubix.entity.communication.Email;
import com.brubix.entity.communication.Phone;
import com.brubix.entity.communication.Social;
import com.brubix.entity.content.Document;
import com.brubix.entity.identity.Role;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.InstitutionAffiliation;
import com.brubix.entity.reference.InstitutionType;
import com.brubix.entity.reference.Language;
import com.brubix.entity.reference.Subject;
import com.brubix.service.controller.inventory.AddressData;
import com.brubix.service.controller.inventory.SocialData;
import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.KnowYourSchoolData;
import com.brubix.service.controller.inventory.school.SchoolForm;
import com.brubix.service.controller.inventory.school.SubjectForm;
import com.brubix.service.generator.CodeGenerator;
import com.brubix.service.repository.inventory.InstitutionRepository;
import com.brubix.service.validator.InstitutionRegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class InstitutionCommandHandlerImpl implements InstitutionCommandHandler {

    private InstitutionRepository schoolRepository;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;

    private CodeGenerator schoolCodeGenerator;
    private SchoolFormCustomValidator schoolFormCustomValidator;
    private SubjectRepository subjectRepository;
    private InstitutionAffiliationRepository affiliationRepository;
    private InstitutionTypeRepository institutionTypeRepository;
    private LanguageMediumRepository languageMediumRepository;
    private UserRepository userRepository;
    private InstitutionRegistrationValidator registrationValidator;


    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public InstitutionCommandHandlerImpl(InstitutionRepository schoolRepository,
                                         CountryRepository countryRepository,
                                         StateRepository stateRepository,
                                         CodeGenerator schoolCodeGenerator,
                                         SchoolFormCustomValidator schoolFormCustomValidator,
                                         SubjectRepository subjectRepository,
                                         CityRepository cityRepository,
                                         InstitutionAffiliationRepository affiliationRepository,
                                         InstitutionTypeRepository institutionTypeRepository,
                                         LanguageMediumRepository languageMediumRepository,
                                         UserRepository userRepository,
                                         InstitutionRegistrationValidator registrationValidator) {
        this.schoolRepository = schoolRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.schoolCodeGenerator = schoolCodeGenerator;
        this.schoolFormCustomValidator = schoolFormCustomValidator;
        this.subjectRepository = subjectRepository;
        this.cityRepository = cityRepository;
        this.affiliationRepository = affiliationRepository;
        this.institutionTypeRepository = institutionTypeRepository;
        this.languageMediumRepository = languageMediumRepository;
        this.userRepository = userRepository;
        this.registrationValidator = registrationValidator;
    }


    @Override
    public InstitutionCode create(SchoolForm schoolForm) {

        schoolFormCustomValidator.doValidate(schoolForm);

        log.info("Creating of institution started");
        Institution school = mapToEntity(schoolForm);
        try {
            Institution savedSchool = schoolRepository.save(school);
            log.info("Loading of schools ended");
            return InstitutionCode
                    .builder()
                    .code(savedSchool.getInstitutionCode())
                    .name(savedSchool.getInstitutionName())
                    .build();
        } catch (DataAccessException ex) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(ex.getCause()));
            throw new BrubixException(ErrorCode.LOADING_ERROR);
        }
    }

    @Override
    @Transactional
    public void create(CourseForm courseForm) {
        Institution school = schoolRepository.findByInstitutionCode(courseForm.getSchoolCode());
        if (school == null) {
            throw new BrubixException(ErrorCode.INVALID_INSTITUTION_CODE);
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
    public Institution mapToEntity(SchoolForm schoolForm) {

        registrationValidator.validate(schoolForm);

        Institution school = new Institution();
        school.setInstitutionName(schoolForm.getSchoolInfo().getName());
        school.setInstitutionCode(schoolCodeGenerator.generate());

        // map addresses
        AddressData addressData = schoolForm.getSchoolInfo().getAddress();
        Address address = new Address();
        address.setFirstLine(addressData.getFirstLine());
        address.setSecondLine(addressData.getSecondLine());
        address.setThirdLine(addressData.getThirdLine());
        address.setPinCode(addressData.getPin());
        address.setCountry(countryRepository.findByCode(addressData.getCountry()));
        address.setState(stateRepository.findByCode(addressData.getState()));
        address.setCity(cityRepository.findByCode(addressData.getCity()));

        // map Milestones
        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        //FIXME - association with user entity and identity
        mileStone.setCreatedBy(1);

        //map to social data
        SocialData socialData = schoolForm.getSocial();
        if (anySocialLinkPresent(socialData)) {
            Social social = new Social();
            social.setFaceBook(socialData.getFacebook());
            social.setGPlus(socialData.getGooglePlus());
            social.setLinkedin(socialData.getLinkedin());
            social.setTwitter(socialData.getTwitter());
            school.setSocial(social);
        }

        //map know your institution
        KnowYourSchoolData kys = schoolForm.getSchoolInfo().getKys();
        List<InstitutionAffiliation> affiliations = kys
                .getAffiliations()
                .stream()
                .map(s -> affiliationRepository.findByAffiliation(s))
                .collect(Collectors.toList());

        List<InstitutionType> institutionTypes = kys
                .getAffiliations()
                .stream()
                .map(s -> institutionTypeRepository.findByType(s))
                .collect(Collectors.toList());

        List<Language> languages = kys
                .getAffiliations()
                .stream()
                .map(s -> languageMediumRepository.findByType(s))
                .collect(Collectors.toList());

        schoolForm
                .getAdminInfos()
                .stream()
                .map(adminInfoData -> {
                    NonFaculty u = new NonFaculty();
                    u.setFirstName(adminInfoData.getFirstName());
                    u.setLastName(adminInfoData.getLastName());

                    Email email = new Email();
                    email.setEmail(adminInfoData.getEmail());
                    u.setEmails(Arrays.asList(email));

                    Phone phone = new Phone();
                    phone.setNumber(adminInfoData.getPhone());
                    u.setPhones(Arrays.asList(phone));

                    Role role = new Role();
                    role.setName(com.brubix.common.model.Role.ADMIN.getName());

                    return u;
                });


        school.setAbout(schoolForm.getSchoolInfo().getAbout());
        school.setAffiliations(affiliations);
        school.setInstitutionTypes(institutionTypes);
        school.setLanguages(languages);
        school.setAddress(address);
        school.setMileStone(mileStone);


        return school;
    }

    private boolean anySocialLinkPresent(SocialData socialData) {
        return socialData != null && (StringUtils.isNotBlank(socialData.getFacebook())
                || StringUtils.isNotBlank(socialData.getGooglePlus())
                || StringUtils.isNotBlank(socialData.getLinkedin())
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
