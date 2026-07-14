package com.forge.service;

import com.forge.dto.ApiResponse;
import com.forge.dto.RegisterRequest;
import com.forge.entity.User;
import com.forge.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.forge.service.JwtService;
import com.forge.dto.LoginRequest;
import com.forge.dto.LoginResponse;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    
    public ApiResponse<String> register(RegisterRequest request) {

        User user = new User(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        userRepository.save(user);

        return new ApiResponse<>(
                true,
                "User registered successfully",
                request.email()
        );
    }
public ApiResponse<LoginResponse> login(LoginRequest request) {

    User user = userRepository.findByEmail(request.email())
            .orElseThrow(() ->
                    new RuntimeException("Invalid email or password"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtService.generateToken(user.getEmail());

    return new ApiResponse<>(
            true,
            "Login successful",
            new LoginResponse(token)
    );
}  
public AuthService(UserRepository userRepository,
                   PasswordEncoder passwordEncoder,
                   JwtService jwtService) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
}
}