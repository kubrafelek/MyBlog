package com.software.myblog.helper;

import com.software.myblog.entity.Role;
import com.software.myblog.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Collections;

public final class DataCreator {

    private DataCreator(){
        throw new UnsupportedOperationException();
    }

    public static Collection<User> createUsers(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername("ayse");
        user.setPassword(passwordEncoder.encode("ayse123"));

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        user.addRoles(Collections.singleton(adminRole));

        return Collections.singletonList(user);
    }
}
