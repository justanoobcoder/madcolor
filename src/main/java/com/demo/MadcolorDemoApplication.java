package com.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@SecurityScheme(
        name = "token_auth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Swagger Mad Colors",
                description = "This is just a demo for RESTful API using Spring Boot",
                version = "1.0"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server")
        },
        tags = {
                @Tag(name = "authentication", description = "Login endpoint"),
                @Tag(name = "customer", description = "REST API endpoints for customer operations"),
                @Tag(name = "category", description = "REST API endpoints for category operations"),
                @Tag(name = "discount", description = "REST API endpoints for discount operations"),
                @Tag(name = "product", description = "REST API endpoints for product operations")
        }
)
public class MadcolorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadcolorDemoApplication.class, args);
    }

}
