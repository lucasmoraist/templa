package com.lucasmoraist.templa.infra.security.filter;

import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.infra.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenGateway tokenGateway;
    private final UserDetailsServiceImpl service;

    public SecurityFilter(TokenGateway tokenGateway, UserDetailsServiceImpl service) {
        this.tokenGateway = tokenGateway;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (token != null && validateTokenAndAuthenticate(token)) {
            log.debug("Authentication successful");
        } else {
            log.warn("Authentication failed or token not provided");
        }

        filterChain.doFilter(request, response);
    }

    private boolean validateTokenAndAuthenticate(String token) {
        String email = this.tokenGateway.validateAndGetSubject(token);

        if (email == null) {
            log.warn("Invalid token");
            return false;
        }

        UserDetails user = this.service.loadUserByUsername(email);

        if (user == null) {
            log.warn("User not found");
            return false;
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("User authenticated: {}", email);

        return true;
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (header == null) {
            log.warn("Token not found");
            return null;
        }

        String token = header.replace("Bearer ", "");
        log.debug("Token found: {}", token);
        return token;
    }

}
