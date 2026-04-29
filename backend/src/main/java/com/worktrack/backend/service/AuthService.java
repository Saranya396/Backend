package com.worktrack.backend.service;

import com.worktrack.backend.dto.AuthRequest;
import com.worktrack.backend.dto.AuthResponse;
import com.worktrack.backend.dto.LoginRequest;
import com.worktrack.backend.entity.User;
import com.worktrack.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail().trim()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = new User();
        user.setFullName(request.getFullName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setGender(request.getGender());
        user.setAge(request.getAge());
        user.setRole(request.getRole());

        return toResponse(userRepository.save(user));
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("User is not registered."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid login credentials.");
        }

        return toResponse(user);
    }

    private AuthResponse toResponse(User user) {
        return new AuthResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getGender(),
                user.getAge(),
                user.getRole()
        );
    }
}
