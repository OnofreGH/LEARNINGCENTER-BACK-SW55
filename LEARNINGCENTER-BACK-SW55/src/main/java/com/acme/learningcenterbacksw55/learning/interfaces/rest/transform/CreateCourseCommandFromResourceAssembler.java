package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.CreateCourseResource;

public class CreateCourseCommandFromResourceAssembler {
    public static CreateCourseCommand toCommandFromResource(CreateCourseResource resource) {
        return new CreateCourseCommand(resource.title(), resource.description());
    }
}
