package com.acme.learningcenterbacksw55.learning.domain.model.commands;

import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record RequestEnrollmentCommand(AcmeStudentRecordId studentRecordId, Long courseId) {
}
