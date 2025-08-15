package com.lucasmoraist.templa.infra.web.response.group;

import com.lucasmoraist.templa.infra.web.response.student.StudentGroupResponse;

import java.util.List;

public record GroupDetails(
        String id,
        String dayOfWeek,
        String startTime,
        String endTime,
        int maxStudents,
        List<StudentGroupResponse> students
) {

}
