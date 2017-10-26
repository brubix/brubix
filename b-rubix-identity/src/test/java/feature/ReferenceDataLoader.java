package feature;


import com.brubix.entity.identity.Privilege;
import com.brubix.entity.identity.Role;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import com.brubix.identity.repository.CountryRepository;
import com.brubix.identity.repository.PrivilegeRepository;
import com.brubix.identity.repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;


    @PostConstruct
    public void load() {
        loadCountry();
        loadRolesAndPrivileges();
    }


    private void loadRolesAndPrivileges() {
        Privilege affiliations = new Privilege();
        affiliations.setName("/affiliations");
        affiliations.setDescription("All affiliations endpoints privilege");

        Privilege institutions = new Privilege();
        institutions.setName("/institutions");
        institutions.setDescription("All institutions endpoints privilege");

        Privilege languages = new Privilege();
        languages.setName("/languages");
        languages.setDescription("All languages endpoints privilege");


        Privilege subjects = new Privilege();
        subjects.setName("/subjects");
        subjects.setDescription("All subjects endpoints privilege");


        Privilege countries = new Privilege();
        countries.setName("/countries");
        countries.setDescription("All countries endpoints privilege");

        Privilege schools = new Privilege();
        schools.setName("/schools");
        schools.setDescription("All schools endpoints privilege");

        Privilege documents = new Privilege();
        documents.setName("/documents");
        documents.setDescription("All documents endpoints privilege");

        privilegeRepository.save(Arrays.asList(affiliations, institutions, languages,
                subjects, countries, schools, documents));

        Role teacher = new Role();
        teacher.setName("TEACHER");
        teacher.setDescription("Can access teacher functionality of a particular school");

        Role student = new Role();
        student.setName("STUDENT");
        student.setDescription("Can access student functionality of a particular school");

        Role parent = new Role();
        parent.setName("PARENT");
        parent.setDescription("Can access parent functionality of a particular school");

        Role guardian = new Role();
        guardian.setName("GUARDIAN");
        guardian.setDescription("Can access guardian functionality of a particular school");

        Role admin = new Role();
        admin.setName("ADMINISTRATOR");
        admin.setDescription("The administrator has access to all functionality of particular school");
        admin.setPrivileges(Arrays.asList(affiliations, institutions, languages,
                subjects, countries, schools, documents));


        Role superAdmin = new Role();
        superAdmin.setName("SUPER ADMINISTRATOR");
        superAdmin.setDescription("The administrator has access to all functionality of particular school");
        superAdmin.setPrivileges(Arrays.asList(affiliations, institutions, languages,
                subjects, countries, schools, documents));

        roleRepository.save(Arrays.asList(teacher, student, parent, guardian, admin, superAdmin));


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
        privilegeRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
