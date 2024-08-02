package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.commands.RequestEnrollmentCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.RequestEnrollmentResource;

public class RequestEnrollmentCommandFromResourceAssembler {
    public static RequestEnrollmentCommand toCommandFromResource(RequestEnrollmentResource resource){
        return new RequestEnrollmentCommand(
                new AcmeStudentRecordId(resource.studentRecordId()),
                resource.courseId());
    }
}
