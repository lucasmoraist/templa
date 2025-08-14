package com.lucasmoraist.templa.infra.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Templa API")
                        .description("Sistema de agendamento online para aulas e cursos.")
                        .summary("API para gerenciamento de agendamentos de aulas e cursos")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Lucas de Morais Nascimento Taguchi")
                                .email("luksmnt1101@gmail.com")
                                .url("https://lmdeveloper.com/")
                        )
                        .license(new License())
                )
                .servers(List.of(
                        new Server()
                                .description("Local Server")
                                .url("http://localhost:8080")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

}
