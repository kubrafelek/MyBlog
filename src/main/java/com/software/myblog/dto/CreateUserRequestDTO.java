package com.software.myblog.dto;

import com.software.myblog.model.Role;

import java.util.Set;

public record CreateUserRequestDTO(Long id,
                                   String fullName,
                                   String email,
                                   String username,
                                   String password,
                                   Set<Role> roles) {
}
