package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
        return new CreateStudentCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.zipCode(),
                resource.country()
        );
    }
}
