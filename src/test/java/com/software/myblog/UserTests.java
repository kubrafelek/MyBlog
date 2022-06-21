package com.software.myblog;

import com.software.myblog.entity.Role;
import com.software.myblog.entity.User;
import com.software.myblog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserTests {

    @Autowired
    public UserRepository userRepository;

    @Test
    public void should_create_success_user() {
        User user = new User();
        user.setUsername("kubrafelek");
        user.setPassword("password");

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        user.addRoles(Set.of(adminRole, userRole));

        userRepository.save(user);
        assertThat(user.getId()).isNotNull();

        Optional<User> optionalUser = userRepository.findById(user.getId());
        assertThat(optionalUser).isPresent();

        User user1 = optionalUser.get();
        assertThat(user1.getUsername()).isNotEmpty();
        assertThat(user1.getUsername()).isEqualTo("kubrafelek");
        assertThat(user1.getPassword()).isNotEmpty();
        assertThat(user1.getPassword()).isEqualTo("password");
        assertThat(user1.getRoles()).hasSize(2);
    }

    @Test
    public void should_match_encoded_user_password(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("kubrafelek");
        user.setPassword(passwordEncoder.encode("password"));

        userRepository.save(user);
        assertThat(user.getId()).isNotNull();

        Optional<User> optionalUser = userRepository.findById(user.getId());
        assertThat(optionalUser).isPresent();

        User user1 = optionalUser.get();
        assertThat(passwordEncoder.matches("password", user1.getPassword()));
    }

    @Test
    public void should_not_match_encoded_user_password(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("kubrafelek");
        user.setPassword(passwordEncoder.encode("password"));

        userRepository.save(user);
        assertThat(user.getId()).isNotNull();

        Optional<User> optionalUser = userRepository.findById(user.getId());
        assertThat(optionalUser).isPresent();

        User user1 = optionalUser.get();
        assertThat(passwordEncoder.matches("pssord", user1.getPassword()));
    }

}







