package com.software.myblog.converter;

import com.software.myblog.dto.CreateUserRequestDTO;
import com.software.myblog.dto.GetUserResponseDTO;
import com.software.myblog.model.User;

public interface UserConverter {

    User toUser(CreateUserRequestDTO createUserRequestDTO);

    CreateUserRequestDTO toCreateUserRequest(User user);

    GetUserResponseDTO toCreateUserResponse(User user);
}
