package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.person.SavePersonCase;
import com.lucasmoraist.templa.domain.model.Person;
import com.lucasmoraist.templa.domain.model.User;
import com.lucasmoraist.templa.infra.mapper.PersonMapper;
import com.lucasmoraist.templa.infra.web.request.CreatePersonRequest;
import com.lucasmoraist.templa.infra.web.response.PersonResponse;
import com.lucasmoraist.templa.infra.web.routes.PersonRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonRoutes {

    private final SavePersonCase savePersonCase;

    @Override
    public ResponseEntity<PersonResponse> createPerson(CreatePersonRequest request) {
        User user = new User(null, request.email(), request.password(), request.role());
        Person person = this.savePersonCase.execute(request.name(), user);
        PersonResponse response = PersonMapper.toResponse(person);
        URI location = URI.create("/api/v1/person");
        return ResponseEntity.created(location).body(response);
    }

}
