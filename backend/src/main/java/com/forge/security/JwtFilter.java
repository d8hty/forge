package com.forge.security;

import com.forge.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        System.out.println("JWT Received: " + token);

        boolean valid = jwtService.isTokenValid(token);
        System.out.println("Token valid: " + valid);

        if (valid) {

            String email = jwtService.extractEmail(token);
            System.out.println("Email: " + email);

            var authentication =
                    new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            Collections.emptyList()
                    );

            System.out.println("Authenticated: " + authentication.isAuthenticated());

            org.springframework.security.core.context.SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            System.out.println(
                    org.springframework.security.core.context.SecurityContextHolder
                            .getContext()
                            .getAuthentication()
            );
        }

        filterChain.doFilter(request, response);
    }
}