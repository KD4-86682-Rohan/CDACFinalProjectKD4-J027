package com.packersandmovers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableMethodSecurity // ✅ Replaces @EnableGlobalMethodSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder enc;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private CustomAuthenticationEntryPoint authEntry;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for JWT-based authentication
            .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntry)) // ✅ Handle unauthorized access
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/users/signin", "/users/signup", "/vendor/signup").permitAll()
            	    .requestMatchers(HttpMethod.OPTIONS).permitAll()
            	    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // ✅ Swagger endpoints
            	    .requestMatchers("/service-requests/**").hasRole("CUSTOMER")
            	    .requestMatchers("/vendor-weight-pricing").hasRole("VENDOR")
            	    .anyRequest().authenticated()
            	)

            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ Enforce stateless sessions
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // ✅ Add JWT authentication filter

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(new Info().title("My REST API")
                .description("Some custom description of API.")
                .version("1.0").contact(new Contact().name("Sallo Szrajbman")
                    .email("salloszraj@gmail.com").url("https://www.baeldung.com")) // ✅ Fixed email and URL
                .license(new License().name("License of API")
                    .url("API license URL")));
    }
}
