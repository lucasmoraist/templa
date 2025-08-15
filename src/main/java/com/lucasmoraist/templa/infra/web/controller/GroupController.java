package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.group.CreateGroupCase;
import com.lucasmoraist.templa.application.usecases.group.GetGroupByIdCase;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.infra.mapper.GroupMapper;
import com.lucasmoraist.templa.infra.web.request.group.GroupRequest;
import com.lucasmoraist.templa.infra.web.response.group.GroupDetails;
import com.lucasmoraist.templa.infra.web.response.group.GroupResponse;
import com.lucasmoraist.templa.infra.web.routes.GroupRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
public class GroupController implements GroupRoutes {

    private final CreateGroupCase createGroupCase;
    private final GetGroupByIdCase getGroupByIdCase;

    public GroupController(CreateGroupCase createGroupCase, GetGroupByIdCase getGroupByIdCase) {
        this.createGroupCase = createGroupCase;
        this.getGroupByIdCase = getGroupByIdCase;
    }

    @Override
    public ResponseEntity<GroupResponse> createGroup(UUID courseId, GroupRequest request) {
        Group group = createGroupCase.execute(courseId, GroupMapper.toDomain(request));
        GroupResponse response = GroupMapper.toResponse(group);
        URI location = URI.create("/api/v1/group");
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<GroupDetails> getGroupById(UUID id) {
        Group group = getGroupByIdCase.execute(id);
        GroupDetails response = GroupMapper.toDetails(group);
        return ResponseEntity.ok(response);
    }

}
