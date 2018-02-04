package com.brubix.service.validator;

import com.brubix.common.exception.BrubixException;
import com.brubix.common.exception.error.ErrorCode;
import com.brubix.common.repository.UserRepository;
import com.brubix.entity.identity.User;
import com.brubix.entity.inventory.Institution;
import com.brubix.service.controller.inventory.SocialData;
import com.brubix.service.controller.inventory.school.AdminInfoData;
import com.brubix.service.controller.inventory.school.InstitutionCreateRequest;
import com.brubix.service.controller.inventory.school.SchoolInfoData;
import com.brubix.service.repository.inventory.InstitutionRepository;
import com.brubix.service.repository.social.SocialRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Sanjaya on 28/01/18.
 */
@Slf4j
public class InstitutionRegistrationValidator {

    private final InstitutionRepository schoolRepository;
    private final UserRepository userRepository;
    private final SocialRepository socialRepository;


    public InstitutionRegistrationValidator(InstitutionRepository schoolRepository,
                                            UserRepository userRepository,
                                            SocialRepository socialRepository) {
        this.schoolRepository = schoolRepository;
        this.userRepository = userRepository;
        this.socialRepository = socialRepository;
    }

    public void validate(InstitutionCreateRequest schoolForm) {
        log.info("Validating registration detail of institution");
        // validate institution
        SchoolInfoData schoolInfoData = schoolForm.getSchoolInfo();
        Institution school = schoolRepository.findByInstitutionName(schoolInfoData.getName());
        if (school != null) {
            throw new BrubixException(ErrorCode.ALREADY_INSTITUTION_REGISTERED);
        }

        // validate user by email/phone, all entered data to be validated
        List<AdminInfoData> adminInfo = schoolForm.getAdminInfos();
        adminInfo
                .forEach(s -> {
                    User userByEmail = userRepository.findByEmailId(s.getEmail());
                    if (userByEmail != null) {
                        throw new BrubixException(ErrorCode.ALREADY_USER_REGISTERED);
                    }

                    User userByPhone = userRepository.findByPhoneNumber(s.getPhone());
                    if (userByPhone != null) {
                        throw new BrubixException(ErrorCode.ALREADY_USER_REGISTERED);
                    }
                });


        // validate institution social ids
        SocialData social = schoolForm.getSocial();
        if (StringUtils.isNotBlank(social.getFacebook()) && socialRepository.findByFaceBook(social.getFacebook()) != null) {
            throw new BrubixException(ErrorCode.ALREADY_PRESENT_FACEBOOK);
        }

        if (StringUtils.isNotBlank(social.getGooglePlus()) && socialRepository.findByGPlus(social.getGooglePlus()) != null) {
            throw new BrubixException(ErrorCode.ALREADY_PRESENT_GPLUS);
        }

        if (StringUtils.isNotBlank(social.getTwitter()) && socialRepository.findByTwitter(social.getTwitter()) != null) {
            throw new BrubixException(ErrorCode.ALREADY_PRESENT_TWITTER);
        }

        if (StringUtils.isNotBlank(social.getLinkedin()) && socialRepository.findByLinkedin(social.getLinkedin()) != null) {
            throw new BrubixException(ErrorCode.ALREADY_PRESENT_LINKEDIN);
        }
    }
}
