package com.example.nserver.system;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
    @Value("${springdoc.api-docs.common.version}")         String apiVersion;
    @Value("${springdoc.api-docs.common.title}")           String apiTitle;
    @Value("${springdoc.api-docs.common.description}")     String apiDescription;
    @Value("${springdoc.api-docs.common.termsOfService}")  String apiTermsOfService;
    @Value("${springdoc.api-docs.common.license}")         String apiLicense;
    @Value("${springdoc.api-docs.common.licenseUrl}")      String apiLicenseUrl;
    @Value("${springdoc.api-docs.common.externalDocDesc}") String apiExternalDocDesc;
    @Value("${springdoc.api-docs.common.externalDocUrl}")  String apiExternalDocUrl;
    @Value("${springdoc.api-docs.common.contact.name}")    String apiContactName;
    @Value("${springdoc.api-docs.common.contact.url}")     String apiContactUrl;
    @Value("${springdoc.api-docs.common.contact.email}")   String apiContactEmail;
//    @Value("${api.query-service.get-all-mapping-products.description}") String apiQSrvGetAllDescription;

//    @Bean
//    public OpenAPI getOpenApiDocumentation() {
//        return new OpenAPI()
//                .info(new Info().title(apiTitle)
//                        .description(apiDescription)
//                        .version(apiVersion)
//                        .contact(new Contact()
//                                .name(apiContactName)
//                                .url(apiContactUrl)
//                                .email(apiContactEmail))
//                        .termsOfService(apiTermsOfService)
//                        .license(new License()
//                                .name(apiLicense)
//                                .url(apiLicenseUrl)))
//                .externalDocs(new ExternalDocumentation()
//                        .description(apiExternalDocDesc)
//                        .url(apiExternalDocUrl));
//    }
@Bean
public OpenAPI baseOpenAPI() {

    return new OpenAPI()
                    .info(new Info().title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name(apiContactName)
                                .url(apiContactUrl)
                                .email(apiContactEmail))
                        .termsOfService(apiTermsOfService)
                        .license(new License()
                                .name(apiLicense)
                                .url(apiLicenseUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(apiExternalDocDesc)
                        .url(apiExternalDocUrl));

}

//@Bean
//public OpenAPI queryserviceOpenAPI() {
//
//    return new OpenAPI()
//            .info(new Info().title(apiQSrvGetAllDescription));
//}
}