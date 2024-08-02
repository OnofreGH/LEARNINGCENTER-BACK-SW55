package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.UpdateCourseResource;

public class UpdateCourseCommandFromResourceAssembler {
    public static UpdateCourseCommand toCommandFromResource(Long courseId, UpdateCourseResource resource) {
        return new UpdateCourseCommand(courseId, resource.title(), resource.description());
    }
}
