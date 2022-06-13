package com.software.myblog.service;

import com.software.myblog.dto.CreateUserRequestDTO;

public interface UserService {

    void create(CreateUserRequestDTO userDTO);
}
