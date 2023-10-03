package com.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author claudio.vilas
 * date: 09/2023
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("blog")
                .packagesToScan("com.blog")
                .build();
    }

    @Bean
    public OpenAPI  openAPI(){
        return new OpenAPI()
                .info(new Info().title("Blog de Desarrollo")
                        .description("blog articulos de desarrollo documentacion y test")
                        .version("1.0")
                        .contact(new Contact().name("Caito").email("caitocd@gmail.com"))
                );
    }

}
