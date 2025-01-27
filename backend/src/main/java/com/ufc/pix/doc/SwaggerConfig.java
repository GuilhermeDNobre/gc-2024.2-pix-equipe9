package com.ufc.pix.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Pix API",
                description = "API para sistema bancario",
                version = "1.0"
        ),
        security = @SecurityRequirement(name = "Bearer"),
        servers = {
                @Server(url = "/", description = "Servidor padrão")
        }
)
@SecurityScheme(
        name = "Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("Usuários")
                .pathsToMatch("/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pixApi() {
        return GroupedOpenApi.builder()
                .group("Chaves PIX")
                .pathsToMatch("/pix/**")
                .build();
    }

    @Bean
    public GroupedOpenApi accountsApi() {
        return GroupedOpenApi.builder()
                .group("Contas")
                .pathsToMatch("/accounts/**")
                .build();
    }

    @Bean
    public GroupedOpenApi trasactionApi(){
        return GroupedOpenApi.builder()
                .group("Transações")
                .pathsToMatch("/transactions/**")
                .build();
    }
    @Bean
    public GroupedOpenApi reportsApi(){
        return GroupedOpenApi.builder()
                .group("Relatórios")
                .pathsToMatch("/generate-reports/**")
                .build();
    }

}
