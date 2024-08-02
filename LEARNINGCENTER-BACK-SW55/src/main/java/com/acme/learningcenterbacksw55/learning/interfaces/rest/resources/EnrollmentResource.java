package com.acme.learningcenterbacksw55.learning.interfaces.rest.resources;

public record EnrollmentResource(Long enrollmentId, String studentRecordId,
                                 Long courseId, String status) {
}
