package com.smartfin.api.configuracion;

import com.smartfin.api.infraestructura.seguridad.FiltroJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class FiltroSeguridadConfig {

    private final FiltroJwt filtroJwt;

    public FiltroSeguridadConfig(FiltroJwt filtroJwt) {
        this.filtroJwt = filtroJwt;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Rutas públicas (Swagger + Auth)
        String[] PUBLICOS = {
                "/autenticacion/**",
                "/error",

                // Springdoc OpenAPI (por defecto)
                "/v3/api-docs/**",

                // Si configuraste: springdoc.api-docs.path: /api-docs
                "/api-docs/**",

                // Swagger UI
                "/swagger-ui/**",
                "/swagger-ui.html",

                // Si configuraste: springdoc.swagger-ui.path: /documentacion
                "/documentacion",
                "/documentacion/**"
        };

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLICOS).permitAll()
                        // opcional: permitir preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
