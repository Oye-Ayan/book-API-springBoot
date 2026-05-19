package com.library.bookapi.service;

import com.library.bookapi.model.request.RegisterRequest;
import com.library.bookapi.domain.Role;
import com.library.bookapi.domain.User;
import com.library.bookapi.repository.UserRepository;
import com.library.bookapi.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent())
            throw new RuntimeException("Username already taken");
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword())); // bcrypt!
        user.setRole(req.getRole() != null ? req.getRole() : Role.ROLE_USER); // default is ROLE_USER
        userRepo.save(user);
        return jwtUtil.generateToken(req.getUsername());
    }

    public String login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");
        return jwtUtil.generateToken(username);
    }
}