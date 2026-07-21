package com.forge.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.forge.dto.ApiResponse;
import com.forge.dto.RegisterRequest;
import com.forge.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.forge.dto.LoginRequest;
import com.forge.dto.LoginResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse<String> register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
    @PostMapping("/login")
public ApiResponse<LoginResponse> login(
        @Valid @RequestBody LoginRequest request){

    return authService.login(request);
}
}