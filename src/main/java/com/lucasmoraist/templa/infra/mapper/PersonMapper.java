package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Person;
import com.lucasmoraist.templa.infra.db.entity.PersonEntity;
import com.lucasmoraist.templa.infra.web.response.person.PersonResponse;

public final class PersonMapper {

    public static Person toDomain(PersonEntity entity) {
        return new Person(
                entity.getId(),
                entity.getName(),
                UserMapper.toDomain(entity.getUser())
        );
    }

    public static PersonEntity toEntity(Person person) {
        return new PersonEntity(
                person.id(),
                person.name(),
                UserMapper.toEntity(person.user())
        );
    }

    public static PersonResponse toResponse(Person person) {
        return new PersonResponse(
                person.id().toString(),
                person.name(),
                person.user().email(),
                person.user().role().getRole()
        );
    }

}
