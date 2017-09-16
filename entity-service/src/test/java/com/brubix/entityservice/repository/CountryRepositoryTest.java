package com.brubix.entityservice.repository;

import com.brubix.model.Country;
import com.brubix.model.State;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
// FIXME @DataJpaTest not working with classpath entities
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void shouldSaveCountryAndStates() {

        // given
        Country country = new Country();
        country.setName("IND");
        country.setCurrency("INR");
        country.setDescription("India");

        State state1 = new State();
        state1.setName("KAR");
        state1.setDescription("Karnataka");
        state1.setCountry(country);

        State state2 = new State();
        state2.setName("OD");
        state2.setDescription("Odisha");
        state2.setCountry(country);

        country.setStates(Arrays.asList(state1, state2));

        // when
        countryRepository.save(country);
        // then
        List<Country> countries = countryRepository.findAll();

        Assertions.assertThat(countries)
                .hasSize(1)
                .extracting("name", "currency", "description")
                .contains(tuple("IND", "INR", "India"));

        Assertions.assertThat(countries.get(0).getStates())
                .hasSize(2)
                .extracting("name", "description")
                .contains(tuple("KAR", "Karnataka"),
                        tuple("OD", "Odisha"));
    }
}