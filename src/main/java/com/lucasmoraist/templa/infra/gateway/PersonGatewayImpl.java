package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.PersonGateway;
import com.lucasmoraist.templa.domain.model.Person;
import com.lucasmoraist.templa.infra.db.entity.PersonEntity;
import com.lucasmoraist.templa.infra.db.repository.PersonRepository;
import com.lucasmoraist.templa.infra.mapper.PersonMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PersonGatewayImpl implements PersonGateway {

    private final PersonRepository repository;

    public PersonGatewayImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        log.debug("Saving person: {}", person);
        PersonEntity entity = PersonMapper.toEntity(person);
        this.repository.save(entity);

        Person savedPerson = PersonMapper.toDomain(entity);
        log.debug("Saved person: {}", savedPerson);
        return savedPerson;
    }

}
