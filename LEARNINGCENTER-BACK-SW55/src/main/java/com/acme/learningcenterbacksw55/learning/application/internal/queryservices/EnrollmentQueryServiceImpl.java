package com.acme.learningcenterbacksw55.learning.application.internal.queryservices;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.*;
import com.acme.learningcenterbacksw55.learning.domain.services.EnrollmentQueryService;
import com.acme.learningcenterbacksw55.learning.infraestructure.persistence.jpa.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentQueryServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * Query handler to get student enrollments
     * @param query containing studentRecordId
     * @return List of Enrollments
     */
    @Override
    public List<Enrollment> handle(GetStudentEnrollmentsQuery query) {
        return enrollmentRepository.findAllByAcmeStudentRecordId(query.studentRecordId());
    }

    /**
     * Query handler to get enrolment by id
     * @param query containing enrollmentId
     * @return Enrollment
     */
    @Override
    public Optional<Enrollment> handle(GetEnrollmentByIdQuery query) {
        return enrollmentRepository.findById(query.enrollmentId());
    }

    @Override
    public List<Enrollment> handle(GetAllEnrollmentsQuery query) {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> handle(GetCourseEnrollmentsQuery query) {
        return enrollmentRepository.findAllByCourseId(query.courseId());
    }

    @Override
    public Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query) {
        return enrollmentRepository.findByAcmeStudentRecordIdAndCourseId(query.acmeStudentRecordId(),
                query.courseId());
    }
}
