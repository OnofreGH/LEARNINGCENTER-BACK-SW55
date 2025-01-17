package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.EnrollmentResource;

public class EnrollmentResourceFromEntityAssembler {
    public static EnrollmentResource toResourceFromEntity(Enrollment enrollment) {
        return new EnrollmentResource(enrollment.getId(),
                enrollment.getAcmeStudentRecordId().studentRecordId(),
                enrollment.getCourse().getId(),
                enrollment.getStatus()
                );
    }
}
