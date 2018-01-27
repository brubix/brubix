
package com.brubix.service.configuration;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    private static final String appName = "b-Rubix Service";

    private String version = "v1";


    @Bean
    public Docket customerOrderApis() {
        return getDocket("b-Rubix-service", applicationPaths(), RequestHandlerSelectors.any(), apiInfo(),
                new Tag(appName, String.format("REST Endpoints for %s", "b-Rubix Service")));
    }


    @Bean
    public Docket docket() {
        return getDocket("b-Rubix-service-management", PathSelectors.regex("/manage.*"),
                RequestHandlerSelectors.any(),
                managementInfo(), new Tag(String.format("%s Management", appName),
                        String.format("%s Management Endpoints", appName)));
    }

    private Docket getDocket(String groupName, Predicate<String> pathPattern, Predicate<RequestHandler> apis,
                             ApiInfo apiinfo, Tag tag) {
        return new Docket(DocumentationType.SWAGGER_2).groupName(groupName)
                .select()
                .apis(apis)
                .paths(pathPattern)
                .build()
                .apiInfo(apiinfo)
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(new AlternateTypeRule(
                        typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .tags(tag);
    }

    private Predicate<String> applicationPaths() {
        Predicate<String> inventoryPath = PathSelectors.regex("/schools.*");
        Predicate<String> document = PathSelectors.regex("/documents.*");

        return Predicates.or(inventoryPath,document);
    }

    private ApiInfo apiInfo() {
        return info(String.format("%s Endpoints", appName),
                "This page facilitates the user to try out the b-Rubix Service endpoints.");
    }


    private ApiInfo info(String title, String description) {
        return new ApiInfo(title, description, version, "https://www.brubix.com/",
                new Contact("b-Rubix", "https://www.brubix.com/", "support@brubix.com"), "b-Rubix Proprietary",
                "https://www.brubix.com/", new ArrayList<>());
    }


    private ApiInfo managementInfo() {
        return info(String.format("%s Management Endpoints", appName),
                String.format("Management Endpoints for %s", appName));
    }

}
