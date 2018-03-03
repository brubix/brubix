package feature;


import com.brubix.common.repository.*;
import com.brubix.entity.reference.*;
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

    @Autowired
    private InstitutionTypeRepository institutionTypeRepository;


    @Autowired
    private LanguageMediumRepository languageMediumRepository;

    @Autowired
    private AffiliationRepository institutionAffiliationRepository;

    @PostConstruct
    public void load() {
        loadCountry();
        loadSubjects();
        loadInstitutionTypes();
        loadLanguage();
        loadInstitutionAffiliations();
    }

    private void loadInstitutionAffiliations() {
        Affiliation cbse = new Affiliation();
        cbse.setAffiliation("CBSE");
        cbse.setDescription("Central board of secondary education");

        Affiliation icse = new Affiliation();
        icse.setAffiliation("ICSE");
        icse.setDescription("International council of secondary education");


        Affiliation igcse = new Affiliation();
        igcse.setAffiliation("IGCSE");
        igcse.setDescription("IGCSE");


        Affiliation university = new Affiliation();
        university.setAffiliation("UNIV");
        university.setDescription("University");

        institutionAffiliationRepository.saveAll(Arrays.asList(cbse, icse, igcse, university));
    }

    private void loadLanguage() {
        Language english = new Language();
        english.setType("English");
        english.setDescription("English");

        Language hindi = new Language();
        hindi.setType("Hindi");
        hindi.setDescription("Hindis");


        Language tamil = new Language();
        tamil.setType("Tamil");
        tamil.setDescription("Tamil");

        Language telugu = new Language();
        telugu.setType("Telugu");
        telugu.setDescription("Telugu");

        languageMediumRepository.saveAll(Arrays.asList(english, hindi, tamil, telugu));
    }

    private void loadInstitutionTypes() {
        InstitutionType play = new InstitutionType();
        play.setType("Play");
        play.setDescription("Play institution");

        InstitutionType primary = new InstitutionType();
        primary.setType("Primary");
        primary.setDescription("Primary institution");

        InstitutionType middle = new InstitutionType();
        middle.setType("Middle");
        middle.setDescription("Middle institution");

        InstitutionType secondary = new InstitutionType();
        secondary.setType("Secondary");
        secondary.setDescription("Secondary institution");

        InstitutionType seniorSecondary = new InstitutionType();
        seniorSecondary.setType("Senior Secondary");
        seniorSecondary.setDescription("Senior Secondary institution");

        InstitutionType college = new InstitutionType();
        college.setType("College");
        college.setDescription("College");

        InstitutionType university = new InstitutionType();
        university.setType("University");
        university.setDescription("University");

        InstitutionType satelliteInstitute = new InstitutionType();
        satelliteInstitute.setType("Satellite Institute");
        satelliteInstitute.setDescription("Satellite Institute");
        institutionTypeRepository.saveAll(Arrays.asList(play, primary, middle,
                secondary, seniorSecondary, college, university, satelliteInstitute));
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
        subjectRepository.saveAll(Arrays.asList(math, physics, chemistry, biology));
    }

    private void loadCountry() {
        Country india = new Country();
        india.setCode("IND");
        india.setCurrency("INR");
        india.setDescription("India");
        india.setDialingCode("+91");

        State karnataka = new State();
        karnataka.setCode("KAR");
        karnataka.setDescription("Karnataka");
        karnataka.setCountry(india);

        City bangalore = new City();
        bangalore.setCode("BNG");
        bangalore.setDescription("Bangalore");
        karnataka.setCities(Arrays.asList(bangalore));
        bangalore.setState(karnataka);

        State maharastra = new State();
        maharastra.setCode("MAH");
        maharastra.setDescription("Maharastra");
        maharastra.setCountry(india);

        City mumbai = new City();
        mumbai.setCode("BOM");
        mumbai.setDescription("Mumbai");
        maharastra.setCities(Arrays.asList(mumbai));
        mumbai.setState(maharastra);

        india.setStates(Arrays.asList(karnataka, maharastra));

        Country usa = new Country();
        usa.setCode("USA");
        usa.setCurrency("USD");
        usa.setDescription("United states");
        usa.setDialingCode("+1");

        State texas = new State();
        texas.setCode("TXS");
        texas.setDescription("Texas");
        texas.setCountry(usa);

        City arizona = new City();
        arizona.setCode("ARZ");
        arizona.setDescription("Arizona");
        texas.setCities(Arrays.asList(arizona));
        arizona.setState(texas);

        State washington = new State();
        washington.setCode("WDC");
        washington.setDescription("Washington DC");
        washington.setCountry(usa);

        City iowa = new City();
        iowa.setCode("IOW");
        iowa.setDescription("IOWA");
        washington.setCities(Arrays.asList(iowa));
        iowa.setState(washington);

        usa.setStates(Arrays.asList(texas, washington));

        countryRepository.saveAll(Arrays.asList(india, usa));
    }

    @PreDestroy
    public void unload() {
        countryRepository.deleteAll();
        subjectRepository.deleteAll();
        institutionTypeRepository.deleteAll();
        languageMediumRepository.deleteAll();
        institutionAffiliationRepository.deleteAll();
    }
}
