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
 * Created by Sanjaya on 16/09/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
// FIXME @DataJpaTest not working with classpath entities
public class StateRepositoryTest {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void shouldGetCountryOfState() {

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
        countryRepository.save(country);

        // when
        List<State> states = stateRepository.findAll();

        Assertions.assertThat(states)
                .hasSize(2)
                .extracting("country.name", "country.currency", "country.description",
                        "name", "description")
                .contains(tuple("IND", "INR", "India", "KAR", "Karnataka"),
                        tuple("IND", "INR", "India", "OD", "Odisha"));


    }
}
