package com.software.myblog.controller;

import com.software.myblog.dto.CreateUserRequestDTO;
import com.software.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserRequestDTO userDTO){
        userService.create(userDTO);
        return ResponseEntity.ok().build();
    }
}
