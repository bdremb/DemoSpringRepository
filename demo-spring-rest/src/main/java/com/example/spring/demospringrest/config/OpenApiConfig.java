package com.example.spring.demospringrest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApiDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local env");

        Server productionServer = new Server();
        productionServer.setUrl("http://some.prod.url");
        productionServer.setDescription("Production env");

        Contact contact = new Contact();
        contact.setName("Sergey Petrov");
        contact.setEmail("mail@mail.ru");
        contact.setUrl("http://some.example.url");

        License meetLicense = new License().name("GNU AGPLv3")
                .url("https://chooselicense.com/license/agpl-3.0/");

        Info info = new Info()
                .title("Client orders API")
                .version("1.0")
                .contact(contact)
                .description("API for client orders")
                .termsOfService("http://some.terms.url")
                .license(meetLicense);

        return new OpenAPI().info(info).servers(List.of(localhostServer, productionServer));
    }
}
