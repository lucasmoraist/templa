package com.lucasmoraist.templa.infra.web.handler;

import com.lucasmoraist.templa.domain.exception.ConflictTimesException;
import com.lucasmoraist.templa.domain.exception.CredentialsException;
import com.lucasmoraist.templa.domain.exception.GroupFullException;
import com.lucasmoraist.templa.domain.exception.TokenException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConflictTimesException.class)
    protected ResponseEntity<String> handleConflictTimesException(ConflictTimesException ex) {
        log.error("Conflict times exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(CredentialsException.class)
    protected ResponseEntity<String> handleCredentialsException(CredentialsException ex) {
        log.error("Credentials exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(GroupFullException.class)
    protected ResponseEntity<String> handleGroupFullException(GroupFullException ex) {
        log.error("Group full exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(403).body(ex.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    protected ResponseEntity<Void> handleTokenException(TokenException ex) {
        log.error("Token exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(403).build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("Entity not found exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Void> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("Username not found exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<DataException>> handleDataRequestException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(DataException::new)
                .toList();
        log.error("Data validation exception occurred: {}", errors, ex);

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Void> handleGenericException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(500).build();
    }

    public record DataException(String label, String message) {
        public DataException(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
