package com.brubix.reference.configuration;

import com.brubix.common.repository.*;
import com.brubix.reference.service.affiliation.AffiliationCommandHandler;
import com.brubix.reference.service.affiliation.AffiliationCommandHandlerImpl;
import com.brubix.reference.service.affiliation.AffiliationQueryHandler;
import com.brubix.reference.service.affiliation.AffiliationQueryHandlerImpl;
import com.brubix.reference.service.country.CountryCommandHandler;
import com.brubix.reference.service.country.CountryCommandHandlerImpl;
import com.brubix.reference.service.country.CountryQueryHandler;
import com.brubix.reference.service.country.CountryQueryHandlerImpl;
import com.brubix.reference.service.institutiontype.InstitutionTypeCommandHandler;
import com.brubix.reference.service.institutiontype.InstitutionTypeCommandHandlerImpl;
import com.brubix.reference.service.institutiontype.InstitutionTypeQueryHandler;
import com.brubix.reference.service.institutiontype.InstitutionTypeQueryHandlerImpl;
import com.brubix.reference.service.language.LanguageCommandHandler;
import com.brubix.reference.service.language.LanguageCommandHandlerImpl;
import com.brubix.reference.service.language.LanguageQueryHandler;
import com.brubix.reference.service.language.LanguageQueryHandlerImpl;
import com.brubix.reference.service.subject.SubjectCommandHandler;
import com.brubix.reference.service.subject.SubjectCommandHandlerImpl;
import com.brubix.reference.service.subject.SubjectQueryHandler;
import com.brubix.reference.service.subject.SubjectQueryHandlerImpl;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Configuration
public class ReferenceDataApiConfiguration {


    @Bean
    public CountryCommandHandler countryCommandHandler(CountryRepository countryRepository) {
        return new CountryCommandHandlerImpl(countryRepository);
    }

    @Bean
    public CountryQueryHandler countryQueryHandler(CountryRepository countryRepository) {
        return new CountryQueryHandlerImpl(countryRepository);
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
    public InstitutionTypeCommandHandler institutionTypeCommandHandler(InstitutionTypeRepository institutionTypeRepository) {
        return new InstitutionTypeCommandHandlerImpl(institutionTypeRepository);
    }

    @Bean
    public InstitutionTypeQueryHandler institutionTypeQueryHandler(InstitutionTypeRepository institutionTypeRepository) {
        return new InstitutionTypeQueryHandlerImpl(institutionTypeRepository);
    }

    @Bean
    public LanguageCommandHandler languageCommandHandler(LanguageMediumRepository languageMediumRepository) {
        return new LanguageCommandHandlerImpl(languageMediumRepository);
    }

    @Bean
    public LanguageQueryHandler languageQueryHandler(LanguageMediumRepository languageMediumRepository) {
        return new LanguageQueryHandlerImpl(languageMediumRepository);
    }

    @Bean
    public AffiliationCommandHandler affiliationCommandHandlers(InstitutionAffiliationRepository affiliationRepository) {
        return new AffiliationCommandHandlerImpl(affiliationRepository);
    }

    @Bean
    public AffiliationQueryHandler affiliationQueryHandlers(InstitutionAffiliationRepository affiliationRepository) {
        return new AffiliationQueryHandlerImpl(affiliationRepository);
    }


    @Bean
    public Validator localValidatorFactoryBean(@Value("${hibernate.validator.fail-fast}") String failFast) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", failFast)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }


}
