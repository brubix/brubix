package com.brubix.brubixservice.configuration;

import com.brubix.brubixservice.generator.SchoolCodeGenerator;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.inventory.SubjectRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.brubixservice.service.inventory.school.SchoolCommandHandler;
import com.brubix.brubixservice.service.inventory.school.SchoolCommandHandlerImpl;
import com.brubix.brubixservice.service.inventory.school.SchoolQueryHandler;
import com.brubix.brubixservice.service.inventory.school.SchoolQueryHandlerImpl;
import com.brubix.brubixservice.service.reference.country.CountryCommandHandler;
import com.brubix.brubixservice.service.reference.country.CountryCommandHandlerImpl;
import com.brubix.brubixservice.service.reference.country.CountryQueryHandler;
import com.brubix.brubixservice.service.reference.country.CountryQueryHandlerImpl;
import com.brubix.brubixservice.service.reference.subject.SubjectCommandHandler;
import com.brubix.brubixservice.service.reference.subject.SubjectCommandHandlerImpl;
import com.brubix.brubixservice.service.reference.subject.SubjectQueryHandler;
import com.brubix.brubixservice.service.reference.subject.SubjectQueryHandlerImpl;
import com.brubix.brubixservice.validator.SchoolFormCustomValidator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Configuration
public class BrubixServiceConfiguration {

    @Bean
    public CountryCommandHandler countryCommandHandler(CountryRepository countryRepository) {
        return new CountryCommandHandlerImpl(countryRepository);
    }

    @Bean
    public CountryQueryHandler countryQueryHandler(CountryRepository countryRepository) {
        return new CountryQueryHandlerImpl(countryRepository);
    }


    @Bean
    public SchoolCommandHandler schoolCommandHandler(SchoolRepository schoolRepository,
                                                     CountryRepository countryRepository,
                                                     StateRepository stateRepository,
                                                     SchoolCodeGenerator schoolCodeGenerator,
                                                     SchoolFormCustomValidator schoolFormCustomValidator,
                                                     SubjectRepository subjectRepository) {
        return new SchoolCommandHandlerImpl(schoolRepository, countryRepository, stateRepository,
                schoolCodeGenerator, schoolFormCustomValidator, subjectRepository);
    }

    @Bean
    public SchoolQueryHandler schoolQueryHandler(SchoolRepository schoolRepository) {
        return new SchoolQueryHandlerImpl(schoolRepository);
    }

    @Bean
    public SchoolCodeGenerator schoolCodeGenerator(SchoolRepository schoolRepository) {
        return new SchoolCodeGenerator(schoolRepository);
    }

    @Bean
    public SubjectCommandHandler subjectCommandHandler(SubjectRepository subjectRepository) {
        return new SubjectCommandHandlerImpl(subjectRepository);
    }

    @Bean
    public SubjectQueryHandler subjectQueryHandler(SubjectRepository subjectRepository) {
        return new SubjectQueryHandlerImpl(subjectRepository);
    }

    @Bean
    public Validator localValidatorFactoryBean(@Value("${hibernate.validator.fail-fast}") String failFast) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", failFast)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public SchoolFormCustomValidator schoolFormCustomValidator(Validator validator) {
        SpringValidatorAdapter springValidatorAdapter = new SpringValidatorAdapter(validator);
        return new SchoolFormCustomValidator(springValidatorAdapter);
    }

}
