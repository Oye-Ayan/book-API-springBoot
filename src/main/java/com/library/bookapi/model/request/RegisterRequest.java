package com.library.bookapi.model.request;

import com.library.bookapi.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // Optional — defaults to ROLE_USER if not provided
    private Role role = Role.ROLE_USER;
}