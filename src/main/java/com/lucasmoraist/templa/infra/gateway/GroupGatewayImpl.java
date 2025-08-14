package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.CourseGateway;
import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.infra.db.entity.GroupEntity;
import com.lucasmoraist.templa.infra.db.repository.GroupRepository;
import com.lucasmoraist.templa.infra.mapper.GroupMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class GroupGatewayImpl implements GroupGateway {

    private final GroupRepository groupRepository;
    private final CourseGateway courseGateway;

    public GroupGatewayImpl(GroupRepository groupRepository, CourseGateway courseGateway) {
        this.groupRepository = groupRepository;
        this.courseGateway = courseGateway;
    }

    @Override
    public Group create(UUID courseId, Group group) {
        Course course = this.courseGateway.findById(courseId);
        log.debug("Creating group: {} for course: {}", group, course);

        GroupEntity entity = GroupMapper.toEntity(group, course);
        this.groupRepository.save(entity);

        Group createdGroup = GroupMapper.toDomain(entity);
        log.info("Group created successfully: {}", createdGroup);

        return createdGroup;
    }

}
