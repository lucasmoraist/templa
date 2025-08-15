package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.UserGateway;
import com.lucasmoraist.templa.domain.exception.CredentialsException;
import com.lucasmoraist.templa.domain.model.User;
import com.lucasmoraist.templa.infra.db.entity.UserEntity;
import com.lucasmoraist.templa.infra.db.repository.UserRepository;
import com.lucasmoraist.templa.infra.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@Service
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserGatewayImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User verifyCredentials(String email, String password) {
        log.debug("Verifying credentials for user with email: {}", email);
        UserEntity userEntity = this.userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User with email {} not found", email);
                    return new CredentialsException("Email or password is incorrect");
                });

        if (!this.passwordEncoder.matches(password, userEntity.getPassword())) {
            log.error("Password mismatch for user with email: {}", email);
            throw new CredentialsException("Email or password is incorrect");
        }

        log.debug("User with email {} verified successfully", email);
        return UserMapper.toDomain(userEntity);
    }

}
