package com.software.myblog.model;

import lombok.RequiredArgsConstructor;

import java.util.Set;

public record AuthenticationResponse(String token, Set<String> roles) {



}
