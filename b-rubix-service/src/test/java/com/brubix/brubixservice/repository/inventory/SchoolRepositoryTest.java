package com.brubix.brubixservice.repository.inventory;

import com.brubix.brubixservice.repository.content.DocumentRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext
// FIXME @DataJpaTest not working with classpath entities
public class SchoolRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void shouldSaveSchoolDetails() {
        // given
        Country country = new Country();
        country.setCode("IND");
        country.setCurrency("INR");
        country.setDescription("India");
        State state = new State();
        state.setCode("KAR");
        state.setDescription("Karnataka");
        state.setCountry(country);
        country.setStates(Arrays.asList(state));
        countryRepository.save(country);

        Address address = new Address();
        address.setFirstLine("first line");
        address.setSecondLine("second line");
        address.setThirdLine("third line");
        address.setPinCode("pin");
        // only one record in DB, ID is 1
        address.setState(stateRepository.getOne(1L));
        address.setCountry(countryRepository.getOne(1L));


        Document logo = new Document();
        logo.setContent(new String("Sample document data").getBytes());
        logo.setDocumentName("Sample Document");
        logo.setMimeType("text/plain");

        DocumentInfo documentInfo = new DocumentInfo();
        documentInfo.setDocumentType(DocumentType.PROFILE);
        documentInfo.setDocument(logo);

        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        mileStone.setCreatedBy(1);

        School school = new School();
        school.setSchoolName("My School");
        school.setSchoolCode("MSCH");
        school.setAddresses(Arrays.asList(address));
        school.setMileStone(mileStone);
        school.setDocuments(Arrays.asList(documentInfo));

        schoolRepository.save(school);


        School savedSchool = schoolRepository.findAll().get(0);

        assertThat(savedSchool.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.code", "country.code")
                .contains(tuple("first line", "second line", "third line", "pin", "KAR", "IND"));

        assertThat(savedSchool.getMileStone())
                .extracting("createdBy")
                .contains(1);

        assertThat(savedSchool.getSchoolName()).isEqualTo("My School");
        assertThat(savedSchool.getSchoolCode()).isEqualTo("MSCH");

        DocumentInfo profilePictureInfo = savedSchool.getDocuments()
                .stream()
                .filter(document -> document.getDocumentType() == DocumentType.PROFILE)
                .findAny()
                .get();

        assertThat(profilePictureInfo.getDocument())
                .extracting("documentName", "content", "mimeType")
                .contains("Sample Document", "Sample document data".getBytes(), "text/plain");

    }
}
