package com.lucasmoraist.templa.infra.gateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.domain.enums.Roles;
import com.lucasmoraist.templa.domain.model.Token;
import com.lucasmoraist.templa.domain.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Log4j2
@Service
public class TokenGatewayImpl implements TokenGateway {

    private final String secret;
    private final int expiresIn;

    public TokenGatewayImpl(
            @Value("${spring.security.jwt.secret}") String secret,
            @Value("${spring.security.jwt.expires-in}") int expiresIn
    ) {
        this.secret = secret;
        this.expiresIn = expiresIn;
    }

    @Override
    public Token generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("templa")
                    .withSubject(user.email())
                    .withClaim("role", user.role().name())
                    .withExpiresAt(generateExpiresAt())
                    .sign(algorithm);
            log.debug("Token generated for email: {}", user.email());
            return new Token(token, expiresIn);
        } catch (JWTCreationException e) {
            log.error("Error generating token", e);
            throw new RuntimeException("Error generating token", e);
        }
    }

    @Override
    public String validateAndGetSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String subject = JWT.require(algorithm)
                    .withIssuer("templa")
                    .build()
                    .verify(token)
                    .getSubject();
            log.debug("Token validated for subject: {}", subject);
            return subject;
        } catch (JWTVerificationException ex) {
            log.error("Invalid or expired token: {}", token, ex);
            throw new RuntimeException("Invalid or expired token", ex);
        }
    }

    @Override
    public Roles getRoleFromToken(String token) {
        String tokenWithoutBearer = token.replace("Bearer ", "");
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String role = JWT.require(algorithm)
                    .withIssuer("templa")
                    .build()
                    .verify(tokenWithoutBearer)
                    .getClaim("role")
                    .asString();
            log.debug("Role extracted from token: {}", role);
            return Roles.valueOf(role);
        } catch (JWTVerificationException ex) {
            log.error("Invalid or expired token: {}", token, ex);
            throw new RuntimeException("Invalid or expired token", ex);
        }
    }

    @Override
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String subject = JWT.require(algorithm)
                    .withIssuer("templa")
                    .build()
                    .verify(token)
                    .getSubject();
            log.debug("Token validated for subject: {}", subject);
            return subject;
        } catch (JWTVerificationException ex) {
            log.error("Invalid or expired token: {}", token, ex);
            throw new RuntimeException("Invalid or expired token", ex);
        }
    }

    private Instant generateExpiresAt() {
        return LocalDateTime.now().plusMinutes(expiresIn).toInstant(ZoneOffset.of("-03:00"));
    }

}
