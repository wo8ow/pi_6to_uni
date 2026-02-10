package com.smartfin.api.infraestructura.documentacion;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionOpenApi {

    public static final String ESQUEMA_SEGURIDAD = "bearer_jwt";

    @Bean
    public OpenAPI openApiSmartfin() {

        SecurityScheme esquema = new SecurityScheme()
                .name(ESQUEMA_SEGURIDAD)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(new Info()
                        .title("SmartFin API")
                        .description("Api de SmartFin para analisis financiero y toma de decisiones (proyecto universitario).")
                        .version("0.0.1")
                        .license(new License().name("Uso academico")))
                .components(new Components().addSecuritySchemes(ESQUEMA_SEGURIDAD, esquema))
                .addSecurityItem(new SecurityRequirement().addList(ESQUEMA_SEGURIDAD));
    }
}
