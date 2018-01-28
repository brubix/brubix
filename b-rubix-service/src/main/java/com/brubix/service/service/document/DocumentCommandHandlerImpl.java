package com.brubix.service.service.document;

import com.brubix.common.exception.BrubixException;
import com.brubix.common.exception.error.ErrorCode;
import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.DocumentInfo;
import com.brubix.entity.inventory.DocumentType;
import com.brubix.entity.inventory.Institution;
import com.brubix.service.controller.inventory.DocumentData;
import com.brubix.service.controller.inventory.document.DocumentForm;
import com.brubix.service.controller.inventory.document.DocumentFormCustomValidator;
import com.brubix.service.repository.content.DocumentInfoRepository;
import com.brubix.service.repository.inventory.InstitutionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DocumentCommandHandlerImpl implements DocumentCommandHandler {

    private final DocumentInfoRepository documentInfoRepository;
    private final DocumentFormCustomValidator documentCustomValidator;
    private final InstitutionRepository schoolRepository;


    public DocumentCommandHandlerImpl(DocumentInfoRepository documentInfoRepository,
                                      DocumentFormCustomValidator documentCustomValidator,
                                      InstitutionRepository schoolRepository) {
        this.documentInfoRepository = documentInfoRepository;
        this.documentCustomValidator = documentCustomValidator;
        this.schoolRepository = schoolRepository;
    }


    @Transactional
    @Override
    public void upload(DocumentForm documentForm) {
        documentCustomValidator.doValidate(documentForm);
        Institution school = schoolRepository.findByInstitutionCode(documentForm.getUid());
        if (school == null) {
            throw new BrubixException(ErrorCode.INVALID_INSTITUTION_CODE);
        }

        // map KYCs
        List<DocumentData> documentDataList = documentForm.getDocuments();
        List<MultipartFile> multipartFileList = documentForm.getAttachments();
        List<DocumentInfo> documentInfoList = new ArrayList<>();
        for (int i = 0; i < documentDataList.size(); i++) {
            DocumentInfo documentInfo = new DocumentInfo();
            documentInfo.setDocumentType(DocumentType.getType(documentDataList.get(i).getType()));
            documentInfo.setNumber(documentDataList.get(i).getNumber());

            MultipartFile documentFile = multipartFileList.get(i);
            documentInfo.setDocument(documentFile != null ? createDocument(documentFile) : null);
            documentInfoList.add(documentInfo);
        }
        List<DocumentInfo> documentInfos = documentInfoRepository.save(documentInfoList);
        school.setDocuments(documentInfos);
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


    @Override
    public DocumentInfo mapToEntity(DocumentData documentForm) {
        return null;
    }
}
