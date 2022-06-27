package com.software.myblog.controller;

import com.software.myblog.helper.JWTHelper;
import com.software.myblog.model.AuthenticationRequest;
import com.software.myblog.model.AuthenticationResponse;
import com.software.myblog.security.UserDetail;
import com.software.myblog.validator.AuthenticationRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationRequestValidator authenticationRequestValidator
            = new AuthenticationRequestValidator();

    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;

    @PostMapping(path = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationRequestValidator.validate(authenticationRequest);
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Principal userdetail dönüyor
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // token generate edilen yer
       String token =  jwtHelper.generate(authenticationRequest.getUsername());

       return ResponseEntity.ok(new AuthenticationResponse(token,
               userDetail
                       .getAuthorities()
                       .stream()
                       .map(GrantedAuthority::getAuthority)
                       .collect(Collectors.toSet())));
    }
}
