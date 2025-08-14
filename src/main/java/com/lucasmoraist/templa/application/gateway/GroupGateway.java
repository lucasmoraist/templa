package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Group;

import java.util.UUID;

public interface GroupGateway {

    Group create(UUID courseId, Group group);

}
