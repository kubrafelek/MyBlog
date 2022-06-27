package com.software.myblog.config;

import com.software.myblog.entity.User;
import com.software.myblog.helper.DataCreator;
import com.software.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig {

    private final UserRepository userRepository;

    @PostConstruct
    public void onInit(){
        Collection<User> users = DataCreator.createUsers();
        userRepository.saveAll(users);
    }
}
