package feature;


import com.brubix.brubixservice.repository.inventory.SubjectRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.entity.inventory.Subject;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

@TestConfiguration
public class ReferenceDataLoader {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @PostConstruct
    public void load() {
        loadCountry();
        loadSubjects();
    }

    private void loadSubjects() {
        Subject math = new Subject();
        math.setName("Mathematics");
        math.setDescription("Mathematics");

        Subject physics = new Subject();
        physics.setName("Physics");
        physics.setDescription("Physics");

        Subject chemistry = new Subject();
        chemistry.setName("Chemistry");
        chemistry.setDescription("Chemistry");

        Subject biology = new Subject();
        biology.setName("Biology");
        biology.setDescription("Biology");
        subjectRepository.save(Arrays.asList(math, physics, chemistry, biology));
    }

    private void loadCountry() {
        Country india = new Country();
        india.setCode("IND");
        india.setCurrency("INR");
        india.setDescription("India");

        State karnataka = new State();
        karnataka.setCode("KAR");
        karnataka.setDescription("Karnataka");
        karnataka.setCountry(india);

        State maharastra = new State();
        maharastra.setCode("MAH");
        maharastra.setDescription("Maharastra");
        maharastra.setCountry(india);

        india.setStates(Arrays.asList(karnataka, maharastra));

        Country usa = new Country();
        usa.setCode("USA");
        usa.setCurrency("USD");
        usa.setDescription("United states");

        State texas = new State();
        texas.setCode("TXS");
        texas.setDescription("Texas");
        texas.setCountry(usa);

        State washington = new State();
        washington.setCode("WDC");
        washington.setDescription("Washington DC");
        washington.setCountry(usa);

        usa.setStates(Arrays.asList(texas, washington));

        countryRepository.save(Arrays.asList(india, usa));
    }

    @PreDestroy
    public void unload() {
        countryRepository.deleteAll();
    }
}
