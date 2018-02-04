package com.brubix.service.service.institution;

import com.brubix.common.exception.BrubixException;
import com.brubix.common.exception.error.ErrorCode;
import com.brubix.common.repository.*;
import com.brubix.entity.communication.Email;
import com.brubix.entity.communication.Phone;
import com.brubix.entity.communication.Social;
import com.brubix.entity.identity.Role;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.InstitutionAffiliation;
import com.brubix.entity.reference.InstitutionType;
import com.brubix.entity.reference.Language;
import com.brubix.entity.reference.Subject;
import com.brubix.service.controller.inventory.AddressData;
import com.brubix.service.controller.inventory.SocialData;
import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.InstitutionCreateRequest;
import com.brubix.service.controller.inventory.school.KnowYourSchoolData;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class InstitutionCommandHandlerImpl implements InstitutionCommandHandler {

    private InstitutionRepository institutionRepository;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private CodeGenerator institutionCodeGenerator;
    private InstitutionFormCustomValidator institutionFormCustomValidator;
    private SubjectRepository subjectRepository;
    private InstitutionAffiliationRepository affiliationRepository;
    private InstitutionTypeRepository institutionTypeRepository;
    private LanguageMediumRepository languageMediumRepository;
    private InstitutionRegistrationValidator registrationValidator;
    private RoleRepository roleRepository;
    private NonFacultyRepository nonFacultyRepository;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public InstitutionCommandHandlerImpl(InstitutionRepository schoolRepository,
                                         CountryRepository countryRepository,
                                         StateRepository stateRepository,
                                         CodeGenerator schoolCodeGenerator,
                                         InstitutionFormCustomValidator schoolFormCustomValidator,
                                         SubjectRepository subjectRepository,
                                         CityRepository cityRepository,
                                         InstitutionAffiliationRepository affiliationRepository,
                                         InstitutionTypeRepository institutionTypeRepository,
                                         LanguageMediumRepository languageMediumRepository,
                                         NonFacultyRepository nonFacultyRepository,
                                         InstitutionRegistrationValidator registrationValidator,
                                         RoleRepository roleRepository) {
        this.institutionRepository = schoolRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.institutionCodeGenerator = schoolCodeGenerator;
        this.institutionFormCustomValidator = schoolFormCustomValidator;
        this.subjectRepository = subjectRepository;
        this.cityRepository = cityRepository;
        this.affiliationRepository = affiliationRepository;
        this.institutionTypeRepository = institutionTypeRepository;
        this.languageMediumRepository = languageMediumRepository;
        this.nonFacultyRepository = nonFacultyRepository;
        this.roleRepository = roleRepository;
        this.registrationValidator = registrationValidator;
    }


    @Override
    public InstitutionCode create(InstitutionCreateRequest schoolForm) {

        institutionFormCustomValidator.doValidate(schoolForm);

        log.info("Creating of institution started");
        Institution school = mapToEntity(schoolForm);
        try {
            Institution savedSchool = institutionRepository.save(school);
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
        Institution school = institutionRepository.findByInstitutionCode(courseForm.getSchoolCode());
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
    public Institution mapToEntity(InstitutionCreateRequest schoolForm) {

        registrationValidator.validate(schoolForm);

        Institution institution = new Institution();
        institution.setInstitutionName(schoolForm.getSchoolInfo().getName());
        institution.setInstitutionCode(institutionCodeGenerator.generate());

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
            institution.setSocial(social);
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

        institution.setAbout(schoolForm.getSchoolInfo().getAbout());
        institution.setAffiliations(affiliations);
        institution.setInstitutionTypes(institutionTypes);
        institution.setLanguages(languages);
        institution.setAddress(address);
        institution.setMileStone(mileStone);

        List<NonFaculty> admins = schoolForm
                .getAdminInfos()
                .stream()
                .map(adminInfoData -> {
                    NonFaculty u = new NonFaculty();
                    u.setFirstName(adminInfoData.getFirstName());
                    u.setLastName(adminInfoData.getLastName());

                    Email email = new Email();
                    email.setEmail(adminInfoData.getEmail());
                    u.setEmails(Arrays.asList(email));
                    email.setUser(u);

                    Phone phone = new Phone();
                    phone.setNumber(adminInfoData.getPhone());
                    u.setPhones(Arrays.asList(phone));
                    phone.setUser(u);

                    Role admin = roleRepository.findByName(com.brubix.common.model.Role.ADMIN.getName());
                    u.setRoles(Arrays.asList(admin));
                    u.setMileStone(mileStone);
                    u.setVerified(false);
                    u.setPassword(UUID.randomUUID().toString());
                    u.setInstitution(institution);
                    return u;
                }).collect(Collectors.toList());
        institution.setNonFaculties(admins);
        return institution;
    }


    private boolean anySocialLinkPresent(SocialData socialData) {
        return socialData != null && (StringUtils.isNotBlank(socialData.getFacebook())
                || StringUtils.isNotBlank(socialData.getGooglePlus())
                || StringUtils.isNotBlank(socialData.getLinkedin())
                || StringUtils.isNotBlank(socialData.getTwitter()));
    }
}
