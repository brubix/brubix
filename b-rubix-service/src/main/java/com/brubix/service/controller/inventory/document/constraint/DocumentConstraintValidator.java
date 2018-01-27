package com.brubix.service.controller.inventory.document.constraint;

import com.brubix.service.controller.inventory.DocumentData;
import com.brubix.entity.inventory.DocumentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Slf4j
public class DocumentConstraintValidator implements ConstraintValidator<BiFieldMatch, Object> {

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
            List<DocumentData> documents = (List<DocumentData>) wrapper.getPropertyValue(firstFieldName);
            List<MultipartFile> attachments = (List<MultipartFile>) wrapper.getPropertyValue(secondFieldName);

            if (CollectionUtils.isEmpty(documents) || CollectionUtils.isEmpty(attachments)) {
                return false;
            }
            if (documents.size() != attachments.size()) {
                return false;
            }

            // verify all KYC documents should have documents number
            // profile document may not contains number
            for (DocumentData documentData : documents) {
                if (!isDocumentNumberAvailableForKycDocuments(documentData)) {
                    return false;
                }
            }
        } catch (Exception exception) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(exception));
        }
        return true;
    }

    private boolean isDocumentNumberAvailableForKycDocuments(DocumentData documentData) {
        if (DocumentType.getKycDocumentTypes().contains(DocumentType.getType(documentData.getType()))) {
            if (StringUtils.isBlank(documentData.getNumber())) {
                return false;
            }
        }
        return true;
    }
}

