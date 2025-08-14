package com.lucasmoraist.templa.infra.web.response.course;

import com.lucasmoraist.templa.infra.web.response.group.GroupResponse;

import java.util.List;

public record CourseResponse(
        String id,
        String name,
        String description,
        String modality,
        List<GroupResponse> groups
) {

}
