package com.software.myblog.dto;

import com.software.myblog.model.Role;

import java.util.Set;

public record GetUserResponseDTO(Long id,
                                 String username,
                                 String password,
                                 Set<Role> roles) {
}
