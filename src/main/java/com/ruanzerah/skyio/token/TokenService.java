package com.ruanzerah.skyio.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ruanzerah.skyio.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenService {
    private static final String LOGIN_API = "login_api";
    @Value("${token}")
    private String key;

    public String generate(User user) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create().withIssuer(LOGIN_API).withSubject(user.getEmail()).withExpiresAt(expireTokenTime()).sign(algorithm);
    }

    public Optional<String> validate(String token) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        try {
            return Optional.of(JWT.require(algorithm).withIssuer(LOGIN_API).build().verify(token).getSubject());
        } catch (JWTVerificationException | NullPointerException e) {
            return Optional.empty();
        }
    }

    private Instant expireTokenTime() {
        return LocalDateTime.now().plusMinutes(40).toInstant(ZoneOffset.of("-03:00"));
    }
}
