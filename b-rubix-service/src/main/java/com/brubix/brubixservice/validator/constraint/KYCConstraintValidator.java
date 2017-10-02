package com.brubix.brubixservice.validator.constraint;

import com.brubix.brubixservice.controller.inventory.KYCData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
public class KYCConstraintValidator implements ConstraintValidator<BiFieldMatch, Object> {

    private String firstFieldName;

    private String secondFieldName;

    @Override
    public void initialize(final BiFieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            List<KYCData> kycs = (List<KYCData>) wrapper.getPropertyValue(firstFieldName);
            List<MultipartFile> attachments = (List<MultipartFile>) wrapper.getPropertyValue(secondFieldName);

            // validate if KYC attachment provided then kyc details must be provided
            if (!CollectionUtils.isEmpty(attachments)) {
                if (isKycAvailable(kycs)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception exception) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(exception));
        }
        return true;
    }

    private boolean isKycAvailable(List<KYCData> kycs) {
        return kycs
                .stream()
                .filter(kycData -> !(isBlank(kycData.getNumber()) || isBlank(kycData.getType())))
                .findAny().isPresent();

    }
}

