package com.software.myblog.converter;

import com.software.myblog.dto.CreateUserRequestDTO;
import com.software.myblog.dto.GetUserResponseDTO;
import com.software.myblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User toUser(CreateUserRequestDTO createUserRequestDTO) {

        User user = new User();
        user.setFullName(createUserRequestDTO.fullName());
        user.setEmail(createUserRequestDTO.email());
        user.setUsername(createUserRequestDTO.username());
        user.setPassword(createUserRequestDTO.password());
        user.setRoles(createUserRequestDTO.roles());

        return user;
    }

    @Override
    public CreateUserRequestDTO toCreateUserRequest(User user) {
        return new CreateUserRequestDTO(user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles());
    }

    @Override
    public GetUserResponseDTO toCreateUserResponse(User user) {
        return new GetUserResponseDTO(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles());
    }
}
