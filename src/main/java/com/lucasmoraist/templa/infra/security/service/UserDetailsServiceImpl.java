package com.lucasmoraist.templa.infra.security.service;

import com.lucasmoraist.templa.infra.db.entity.UserEntity;
import com.lucasmoraist.templa.infra.db.repository.UserRepository;
import com.lucasmoraist.templa.infra.security.user.UserDetailsImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Searching for user with email: {}", username);
        UserEntity user = this.repository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("User not found");
                    return new UsernameNotFoundException("User not found");
                });
        return new UserDetailsImpl(user);
    }

}
