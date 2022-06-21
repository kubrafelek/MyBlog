package com.software.myblog.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //authentication entry point yakalar eğer hata verir ise error döner
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws
            IOException, ServletException {

        log.error("Unauthorized {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
