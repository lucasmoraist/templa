package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.infra.db.entity.GroupEntity;

import java.util.List;

public final class GroupMapper {

    public static List<Group> toDomainList(List<GroupEntity> groups) {
        return groups.stream()
                .map(GroupMapper::toDomain)
                .toList();
    }

    public static List<GroupEntity> toEntityList(List<Group> groups) {
        return groups.stream()
                .map(GroupMapper::toEntity)
                .toList();
    }

    private static Group toDomain(GroupEntity groupEntity) {
        return new Group(
                groupEntity.getId(),
                groupEntity.getDayOfWeek(),
                groupEntity.getTime(),
                groupEntity.getDuration(),
                groupEntity.getMaxStudents(),
                null
        );
    }

    private static GroupEntity toEntity(Group group) {
        return new GroupEntity(
                group.id(),
                group.dayOfWeek(),
                group.time(),
                group.duration(),
                group.maxStudents(),
                null
        );
    }

}
