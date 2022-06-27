package com.software.myblog.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Verification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;

@Component
@Getter
@Setter
@Slf4j
public class JWTHelper {

    /*
     * validate
     * findUsername
     * generate token
     *
     * signIn de token gelecek validate edilecek
     * logIn olduğunda JWT token üreticez ve bu token kullanılıyor olucak
     *
     * JWT TOKEN Structure
     *
     * Header(imzası, başlığı)
     * Payload (key-value, username, role... tüm datalar)
     * Signature
     *
     * token içinde ki payloaddan user datasına erişicez
     * */

    @Value("${blog.jwt.secret-key:}")
    private String secretKey;

    @Value("${blog.jwt.expires-in:}")
    private long expiresIn;


    public String generate(String username) {
        if (!StringUtils.hasLength(username)) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // JWT Secret key ile oluşturulur
        // 12:00 -> 12:06 expire
        return JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(Date.from(Instant.now()).getTime() + expiresIn))
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String findUsername(String token) {
        if (!StringUtils.hasLength(token)) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        return JWT.decode(token).getClaim("username").asString();
    }

    public boolean validate(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        } catch (AlgorithmMismatchException algorithmMismatchException) {
            log.error("JWT Token AlgorithmMismatchException occured!");
        } catch (SignatureVerificationException signatureVerificationException) {
            log.error("JWT Token SignatureVerificationException occured!");
        } catch (TokenExpiredException tokenExpiredException) {
            log.error("JWT Token TokenExpiredException occured!");
        } catch (InvalidClaimException invalidClaimException) {
            log.error("JWT Token InvalidClaimException occured!");
        }
        return false;
    }
}
