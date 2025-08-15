package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.infra.db.entity.TeacherEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TeacherMapper {

    public static Teacher toDomain(TeacherEntity entity) {
        return new Teacher(
                entity.getId(),
                entity.getName(),
                UserMapper.toDomain(entity.getUser()),
                CourseMapper.toDomainList(entity.getCourses())
        );
    }

    public static TeacherEntity toEntity(Teacher teacher) {
        return new TeacherEntity(
                teacher.id(),
                teacher.name(),
                UserMapper.toEntity(teacher.user()),
                List.of()
        );
    }

    public static Map<String, Object> toResponse(Teacher teacher) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("id", teacher.id());
        response.put("name", teacher.name());

        Map<String, Object> contact = new LinkedHashMap<>();
        contact.put("email", teacher.user().email());
        response.put("contact", contact);

        response.put("role", teacher.user().role().getRole());

        List<Map<String, Object>> courses = teacher.courses().stream()
                .map(course -> {
                    Map<String, Object> courseMap = new LinkedHashMap<>();
                    courseMap.put("id", course.id());
                    courseMap.put("name", course.name());
                    courseMap.put("description", course.description());
                    courseMap.put("modality", course.modality());
                    return courseMap;
                })
                .toList();

        response.put("courses", courses);

        return response;
    }


}
