package com.lucasmoraist.templa.application.usecases.group;

import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.domain.model.Group;

import java.util.UUID;

public class CreateGroupCase {

    private final GroupGateway groupGateway;

    public CreateGroupCase(GroupGateway groupGateway) {
        this.groupGateway = groupGateway;
    }

    public Group execute(UUID courseId, Group group) {
        return this.groupGateway.create(courseId, group);
    }

}
