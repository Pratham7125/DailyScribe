package com.pc.journalApp.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI journalOpenAPI() {

        return new OpenAPI()
                .info(new Info()

                        .title("DailyScribe Project")
                        .version("1.0")
                        .description("""
                                Secure Personal Journal Management Platform.

                                Features:
                                • User Registration & Authentication
                                • Journal CRUD Operations
                                • Weather API Integration
                                • Redis Caching
                                """)
                        .contact(new Contact().name("Pratham Chauhan").email("prathamchauhan0701@gmail.com")));
    }
}