package com.software.myblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BcryptPasswordEncoderTest {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void shoould_match_password_with_bcryptPasswordEncoder() {

        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);
        assertThat(encodedPassword).isNotEmpty();
        assertThat(passwordEncoder.matches(password, encodedPassword)).isTrue();
    }
}
