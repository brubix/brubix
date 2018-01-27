package com.brubix.service.configuration;

import com.brubix.common.repository.*;
import com.brubix.service.controller.inventory.document.DocumentFormCustomValidator;
import com.brubix.service.generator.SchoolCodeGenerator;
import com.brubix.service.repository.content.DocumentInfoRepository;
import com.brubix.service.repository.inventory.SchoolRepository;
import com.brubix.service.service.document.DocumentCommandHandler;
import com.brubix.service.service.document.DocumentCommandHandlerImpl;
import com.brubix.service.service.school.*;
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
    public SchoolCommandHandler schoolCommandHandler(SchoolRepository schoolRepository,
                                                     CountryRepository countryRepository,
                                                     StateRepository stateRepository,
                                                     SchoolCodeGenerator schoolCodeGenerator,
                                                     SchoolFormCustomValidator schoolFormCustomValidator,
                                                     SubjectRepository subjectRepository,
                                                     CityRepository cityRepository,
                                                     InstitutionAffiliationRepository affiliationRepository,
                                                     InstitutionTypeRepository institutionTypeRepository,
                                                     LanguageMediumRepository languageMediumRepository, UserRepository userRepository
    ) {
        return new SchoolCommandHandlerImpl(schoolRepository, countryRepository, stateRepository,
                schoolCodeGenerator, schoolFormCustomValidator, subjectRepository, cityRepository,
                affiliationRepository, institutionTypeRepository, languageMediumRepository, userRepository);
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
    public DocumentCommandHandler documentCommandHandler(DocumentInfoRepository documentInfoRepository,
                                                         DocumentFormCustomValidator documentFormCustomValidator,
                                                         SchoolRepository schoolRepository) {
        return new DocumentCommandHandlerImpl(documentInfoRepository, documentFormCustomValidator, schoolRepository);
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
    public DocumentFormCustomValidator documentFormCustomValidator(Validator validator) {
        SpringValidatorAdapter springValidatorAdapter = new SpringValidatorAdapter(validator);
        return new DocumentFormCustomValidator(springValidatorAdapter);
    }


    @Bean
    public SchoolFormCustomValidator schoolFormCustomValidator(Validator validator) {
        SpringValidatorAdapter springValidatorAdapter = new SpringValidatorAdapter(validator);
        return new SchoolFormCustomValidator(springValidatorAdapter);
    }

}
