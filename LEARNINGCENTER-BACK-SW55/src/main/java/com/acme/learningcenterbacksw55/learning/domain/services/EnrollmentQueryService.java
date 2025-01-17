package com.acme.learningcenterbacksw55.learning.domain.services;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface EnrollmentQueryService {

    List<Enrollment> handle(GetStudentEnrollmentsQuery query);

    Optional<Enrollment> handle(GetEnrollmentByIdQuery query);

    List<Enrollment> handle(GetAllEnrollmentsQuery query);

    List<Enrollment> handle(GetCourseEnrollmentsQuery query);

    Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query);
}
