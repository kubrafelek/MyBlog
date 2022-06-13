package com.software.myblog.service;

import com.software.myblog.converter.UserConverter;
import com.software.myblog.dto.CreateUserRequestDTO;
import com.software.myblog.model.User;
import com.software.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Override
    public void create(CreateUserRequestDTO userDTO) {
        User user = userConverter.toUser(userDTO);
        userRepository.save(user);
    }
}
