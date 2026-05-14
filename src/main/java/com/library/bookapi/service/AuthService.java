package com.library.bookapi.service;

import com.library.bookapi.model.User;
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

    public String register(String username, String password) {
        if (userRepo.findByUsername(username).isPresent())
            throw new RuntimeException("Username already taken");
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // bcrypt!
        userRepo.save(user);
        return jwtUtil.generateToken(username);
    }

    public String login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");
        return jwtUtil.generateToken(username);
    }
}