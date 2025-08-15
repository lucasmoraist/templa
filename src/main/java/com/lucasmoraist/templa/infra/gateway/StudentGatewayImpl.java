package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.infra.db.entity.StudentEntity;
import com.lucasmoraist.templa.infra.db.repository.StudentRepository;
import com.lucasmoraist.templa.infra.mapper.StudentMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class StudentGatewayImpl implements StudentGateway {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    public StudentGatewayImpl(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student save(Student student) {
        log.debug("Saving student: {}", student);
        StudentEntity entity = StudentMapper.toEntity(student);
        entity.getUser().setPassword(this.passwordEncoder.encode(entity.getUser().getPassword()));
        this.repository.save(entity);

        Student savedStudent = StudentMapper.toDomain(entity);
        log.debug("Saved student: {}", savedStudent);
        return savedStudent;
    }

    @Override
    public Student findById(UUID id) {
        return this.repository.findById(id)
                .map(StudentMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new EntityNotFoundException("Student not found");
                });
    }

    @Override
    public Student findByEmail(String email) {
        return this.repository.findByUserEmail(email)
                .map(StudentMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Student not found with email: {}", email);
                    return new EntityNotFoundException("Student not found");
                });
    }

}
