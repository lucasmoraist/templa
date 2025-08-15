package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.TeacherGateway;
import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.infra.db.entity.TeacherEntity;
import com.lucasmoraist.templa.infra.db.repository.TeacherRepository;
import com.lucasmoraist.templa.infra.mapper.TeacherMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class TeacherGatewayImpl implements TeacherGateway {

    private final TeacherRepository repository;
    private final PasswordEncoder passwordEncoder;

    public TeacherGatewayImpl(TeacherRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        log.debug("Saving teacher: {}", teacher);
        TeacherEntity entity = TeacherMapper.toEntity(teacher);
        entity.getUser().setPassword(this.passwordEncoder.encode(entity.getUser().getPassword()));
        this.repository.save(entity);

        Teacher savedTeacher = TeacherMapper.toDomain(entity);
        log.debug("Saved teacher: {}", savedTeacher);
        return savedTeacher;
    }

    @Override
    public Teacher findById(UUID id) {
        return this.repository.findById(id)
                .map(TeacherMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Teacher not found with id: {}", id);
                    return new RuntimeException("Teacher not found");
                });
    }

}
