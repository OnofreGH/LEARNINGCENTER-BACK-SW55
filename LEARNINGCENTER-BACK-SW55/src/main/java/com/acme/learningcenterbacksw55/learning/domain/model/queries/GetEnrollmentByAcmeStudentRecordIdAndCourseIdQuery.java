package com.acme.learningcenterbacksw55.learning.domain.model.queries;

import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(AcmeStudentRecordId acmeStudentRecordId,
                                                                 Long courseId) {
}
