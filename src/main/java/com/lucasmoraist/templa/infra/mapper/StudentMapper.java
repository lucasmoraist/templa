package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.domain.model.Enrollment;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.domain.utils.FormatDuration;
import com.lucasmoraist.templa.infra.db.entity.StudentEntity;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class StudentMapper {

    public static StudentEntity toEntity(Student student) {
        return new StudentEntity(
                student.id(),
                student.name(),
                UserMapper.toEntity(student.user()),
                List.of()
        );
    }

    public static Student toDomain(StudentEntity entity) {
        return new Student(
                entity.getId(),
                entity.getName(),
                UserMapper.toDomain(entity.getUser()),
                entity.getEnrollments().stream()
                        .map(it -> new Enrollment(
                                it.getId(),
                                null,
                                new Group(
                                        it.getGroup().getId(),
                                        it.getGroup().getDayOfWeek(),
                                        it.getGroup().getStartTime(),
                                        it.getGroup().getEndTime(),
                                        it.getGroup().getMaxStudents(),
                                        new Course(
                                                it.getGroup().getCourse().getId(),
                                                it.getGroup().getCourse().getName(),
                                                it.getGroup().getCourse().getDescription(),
                                                it.getGroup().getCourse().getModality(),
                                                new Teacher(
                                                        it.getGroup().getCourse().getTeacher().getId(),
                                                        it.getGroup().getCourse().getTeacher().getName(),
                                                        UserMapper.toDomain(it.getGroup().getCourse().getTeacher().getUser()),
                                                        List.of()
                                                ),
                                                List.of()
                                        ),
                                        null
                                ),
                                it.getEnrollmentDate()
                        ))
                        .toList()
        );
    }

    public static Map<String, Object> toDetails(Student student) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("id", student.id());
        response.put("name", student.name());

        Map<String, Object> contact = new LinkedHashMap<>();
        contact.put("email", student.user().email());
        response.put("contact", contact);

        response.put("role", student.user().role().getRole());

        List<Map<String, Object>> enrollments = student.enrollments().stream()
                .map(it -> {
                    Map<String, Object> enrollment = new LinkedHashMap<>();
                    enrollment.put("enrollment_id", it.id());

                    // course
                    Map<String, Object> course = new LinkedHashMap<>();
                    course.put("course_id", it.group().course().id());
                    course.put("name", it.group().course().name());
                    course.put("description", it.group().course().description());
                    course.put("modality", it.group().course().modality());

                    // teacher
                    Map<String, Object> teacher = new LinkedHashMap<>();
                    teacher.put("teacher_id", it.group().course().teacher().id());
                    teacher.put("name", it.group().course().teacher().name());

                    Map<String, Object> teacherContact = new LinkedHashMap<>();
                    teacherContact.put("email", it.group().course().teacher().user().email());
                    teacher.put("contact", teacherContact);
                    course.put("teacher", teacher);

                    // class
                    Map<String, Object> clazz = new LinkedHashMap<>();
                    clazz.put("id", it.group().id());
                    clazz.put("dayOfWeek", it.group().dayOfWeek().getValue());
                    clazz.put("startTime", it.group().startTime().toString());
                    clazz.put("endTime", it.group().endTime().toString());
                    clazz.put("duration", FormatDuration.format(Duration.between(it.group().startTime(), it.group().endTime()).abs()));
                    course.put("class", clazz);

                    enrollment.put("course", course);
                    enrollment.put("enrollmentDate", it.enrollmentDate().toString());

                    return enrollment;
                })
                .toList();

        response.put("enrollments", enrollments);

        return response;
    }

}
