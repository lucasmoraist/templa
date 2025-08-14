package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.PersonGateway;
import com.lucasmoraist.templa.domain.model.Person;
import com.lucasmoraist.templa.infra.db.entity.PersonEntity;
import com.lucasmoraist.templa.infra.db.repository.PersonRepository;
import com.lucasmoraist.templa.infra.mapper.PersonMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PersonGatewayImpl implements PersonGateway {

    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;

    public PersonGatewayImpl(PersonRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        log.debug("Saving person: {}", person);
        PersonEntity entity = PersonMapper.toEntity(person);
        entity.getUser().setPassword(this.passwordEncoder.encode(entity.getUser().getPassword()));
        this.repository.save(entity);

        Person savedPerson = PersonMapper.toDomain(entity);
        log.debug("Saved person: {}", savedPerson);
        return savedPerson;
    }

}
