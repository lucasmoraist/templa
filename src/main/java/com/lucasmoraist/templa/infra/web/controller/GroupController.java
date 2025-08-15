package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.group.CreateGroupCase;
import com.lucasmoraist.templa.application.usecases.group.GetGroupByIdCase;
import com.lucasmoraist.templa.application.usecases.token.GetRoleByTokenCase;
import com.lucasmoraist.templa.domain.enums.Roles;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.infra.mapper.GroupMapper;
import com.lucasmoraist.templa.infra.web.request.group.GroupRequest;
import com.lucasmoraist.templa.infra.web.routes.GroupRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
public class GroupController implements GroupRoutes {

    private final CreateGroupCase createGroupCase;
    private final GetGroupByIdCase getGroupByIdCase;

    private final GetRoleByTokenCase getRoleByTokenCase;

    public GroupController(CreateGroupCase createGroupCase, GetGroupByIdCase getGroupByIdCase, GetRoleByTokenCase getRoleByTokenCase) {
        this.createGroupCase = createGroupCase;
        this.getGroupByIdCase = getGroupByIdCase;
        this.getRoleByTokenCase = getRoleByTokenCase;
    }

    @Override
    public ResponseEntity<?> createGroup(UUID courseId, GroupRequest request) {
        Group group = createGroupCase.execute(courseId, GroupMapper.toDomain(request));
        Map<String, Object> response = GroupMapper.toResponse(group, Roles.TEACHER);
        URI location = URI.create("/api/v1/group");
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<?> getGroupById(String authorization, UUID id) {
        Group group = getGroupByIdCase.execute(id);
        Roles role = getRoleByTokenCase.execute(authorization);
        Map<String, Object> response = GroupMapper.toResponse(group, role);
        return ResponseEntity.ok(response);
    }

}
