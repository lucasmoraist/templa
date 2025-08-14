package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.person.CreatePersonRequest;
import com.lucasmoraist.templa.infra.web.response.PersonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/person")
public interface PersonRoutes {

    @PostMapping("register")
    ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request);

}
