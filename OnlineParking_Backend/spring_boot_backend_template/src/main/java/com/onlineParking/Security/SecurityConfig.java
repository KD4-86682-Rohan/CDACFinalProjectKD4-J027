//package com.onlineParking.Security;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//
////@EnableWebSecurity//to enable spring sec frmwork support
//@Configuration //to tell SC , this is config class containing @Bean methods
////@EnableGlobalMethodSecurity(prePostEnabled = true)
////To enable method level authorization support : pre n post authorization
//public class SecurityConfig {
//	//dep : pwd encoder
//	@Autowired
//	private PasswordEncoder enc;
//	//dep : custom jwt auth filter
//	@Autowired
//	private JwtAuthenticationFilter jwtFilter;
//	//dep : custom auth entry point
//	@Autowired
//	private CustomAuthenticationEntryPoint authEntry;
//	
//	
//	@Bean
//	SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
//		http
//			.csrf(csrf -> csrf.disable())
//			.authorizeHttpRequests(requests -> 
//			requests
//				.requestMatchers("/authenticate").permitAll()
//				.requestMatchers("/ParkingLocation/**").hasAnyRole("VENDER", "ADMIN")
//				.requestMatchers("/ParkingSlot/**").hasAnyRole("VENDER", "ADMIN")
//				.requestMatchers("/Revenue/**").hasAnyRole("VENDER", "ADMIN")
//				.requestMatchers("/auditLogs/**").hasRole("ADMIN")
//		    	.anyRequest().authenticated()
//		    )
//			.httpBasic(Customizer.withDefaults())
//			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		return http.build();
//	}
//	//configure AuthMgr as a spring bean
//	@Bean
//	public AuthenticationManager authenticationManager
//	(AuthenticationConfiguration config) throws Exception
//	{
//		return config.getAuthenticationManager();
//	}
//	
//	private SecurityScheme createAPIKeyScheme() {
//	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//	        .bearerFormat("JWT")
//	        .scheme("bearer");
//	}
//	
//	@Bean
//	public OpenAPI openAPI() {
//	    return new OpenAPI().addSecurityItem(new SecurityRequirement().
//	            addList("Bearer Authentication"))
//	        .components(new Components().addSecuritySchemes
//	            ("Bearer Authentication", createAPIKeyScheme()))
//	        .info(new Info().title("My REST API")
//	            .description("Some custom description of API.")
//	            .version("1.0").contact(new Contact().name("Sallo Szrajbman")
//	                .email( "www.baeldung.com").url("salloszraj@gmail.com"))
//	            .license(new License().name("License of API")
//	                .url("API license URL")));
//	}
//	
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow frontend
//	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
//}
