package com.brubix.brubixservice.repository.inventory;

import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.groups.Tuple.tuple;

/**
 * Created by sanjeev.singh1 on 14/09/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext
// FIXME @DataJpaTest not working with classpath entities
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Rollback
    public void shouldSaveCountryAndStates() {

        // given
        Country country = new Country();
        country.setCode("IND");
        country.setCurrency("INR");
        country.setDescription("India");

        State state1 = new State();
        state1.setCode("KAR");
        state1.setDescription("Karnataka");
        state1.setCountry(country);

        State state2 = new State();
        state2.setCode("OD");
        state2.setDescription("Odisha");
        state2.setCountry(country);

        country.setStates(Arrays.asList(state1, state2));

        // when
        countryRepository.save(country);
        // then
        List<Country> countries = countryRepository.findAll();

        Assertions.assertThat(countries)
                .hasSize(1)
                .extracting("code", "currency", "description")
                .contains(tuple("IND", "INR", "India"));

        Assertions.assertThat(countries.get(0).getStates())
                .hasSize(2)
                .extracting("code", "description")
                .contains(tuple("KAR", "Karnataka"),
                        tuple("OD", "Odisha"));
    }
}