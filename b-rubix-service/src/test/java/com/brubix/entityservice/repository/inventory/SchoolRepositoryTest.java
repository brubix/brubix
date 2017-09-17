package com.brubix.entityservice.repository.inventory;

import com.brubix.entityservice.repository.content.DocumentRepository;
import com.brubix.entityservice.repository.reference.CountryRepository;
import com.brubix.entityservice.repository.reference.StateRepository;
import com.brubix.model.content.Document;
import com.brubix.model.inventory.Address;
import com.brubix.model.inventory.MileStone;
import com.brubix.model.inventory.School;
import com.brubix.model.reference.Country;
import com.brubix.model.reference.State;
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
        country.setName("IND");
        country.setCurrency("INR");
        country.setDescription("India");
        State state = new State();
        state.setName("KAR");
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

        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        mileStone.setCreatedBy(1);

        School school = new School();
        school.setSchoolName("My School");
        school.setSchoolCode("MSCH");
        school.setAddresses(Arrays.asList(address));
        school.setMileStone(mileStone);
        school.setSchoolLogo(logo);

        schoolRepository.save(school);


        School savedSchool = schoolRepository.findAll().get(0);

        assertThat(savedSchool.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.name", "country.name")
                .contains(tuple("first line", "second line", "third line", "pin", "KAR", "IND"));

        assertThat(savedSchool.getMileStone())
                .extracting("createdBy")
                .contains(1);

        assertThat(savedSchool.getSchoolName()).isEqualTo("My School");
        assertThat(savedSchool.getSchoolCode()).isEqualTo("MSCH");

        assertThat(savedSchool.getSchoolLogo())
                .extracting("documentName", "content", "mimeType")
                .contains("Sample Document", "Sample document data".getBytes(), "text/plain");

    }
}
