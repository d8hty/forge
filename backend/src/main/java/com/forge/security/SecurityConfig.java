package com.forge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final ForgeUserDetailsService userDetailsService;

public SecurityConfig(
        JwtFilter jwtFilter,
        ForgeUserDetailsService userDetailsService) {

    this.jwtFilter = jwtFilter;
    this.userDetailsService = userDetailsService;
}

    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        PasswordEncoder passwordEncoder) throws Exception {

        http
    .csrf(csrf -> csrf.disable())
    .authenticationProvider(authenticationProvider(passwordEncoder))
    .authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/v1/auth/**").permitAll()
    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
    .anyRequest().authenticated()
)
    
    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
   @Bean
public DaoAuthenticationProvider authenticationProvider(
        PasswordEncoder passwordEncoder) {

    DaoAuthenticationProvider provider =
            new DaoAuthenticationProvider(userDetailsService);

    provider.setPasswordEncoder(passwordEncoder);

    return provider;
}
@Bean
public AuthenticationManager authenticationManager(
        AuthenticationConfiguration configuration)
        throws Exception {

    return configuration.getAuthenticationManager();
}
}