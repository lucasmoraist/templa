package com.lucasmoraist.templa.application.usecases.group;

import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.domain.model.Group;

import java.util.UUID;

public class GetGroupByIdCase {

    private final GroupGateway groupGateway;

    public GetGroupByIdCase(GroupGateway groupGateway) {
        this.groupGateway = groupGateway;
    }

    public Group execute(UUID id) {
        return this.groupGateway.findById(id);
    }

}
