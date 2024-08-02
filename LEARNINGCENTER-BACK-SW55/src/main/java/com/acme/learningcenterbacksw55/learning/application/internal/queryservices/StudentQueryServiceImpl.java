package com.acme.learningcenterbacksw55.learning.application.internal.queryservices;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Student;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.GetStudentByProfileIdQuery;
import com.acme.learningcenterbacksw55.learning.domain.services.StudentQueryService;
import com.acme.learningcenterbacksw55.learning.infraestructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentQueryServiceImpl implements StudentQueryService {

    private final StudentRepository studentRepository;

    public StudentQueryServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Query handler to get studente by profileId
     * @param query containing profileId
     * @return Student
     */
    @Override
    public Optional<Student> handle(GetStudentByProfileIdQuery query) {
        return studentRepository.findByProfileId(query.profileId());
    }

    @Override
    public Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query) {
        return studentRepository.findByAcmeStudentRecordId(query.acmeStudentRecordId());
    }
}
