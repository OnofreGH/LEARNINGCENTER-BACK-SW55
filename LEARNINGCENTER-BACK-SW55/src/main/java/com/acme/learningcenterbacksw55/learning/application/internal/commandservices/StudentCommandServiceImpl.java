package com.acme.learningcenterbacksw55.learning.application.internal.commandservices;

import com.acme.learningcenterbacksw55.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learningcenterbacksw55.learning.domain.services.StudentCommandService;
import com.acme.learningcenterbacksw55.learning.infraestructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

    private final StudentRepository studentRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        return null;
    }

    @Override
    public AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.studentRecordId()).map(student -> {
            //Update student metrics
            student.updateMetricsOnTutorialCompleted();
            studentRepository.save(student);
            return  student.getAcmeStudentRecordId();
        }).orElseThrow(()-> new IllegalArgumentException("Student with given Id not found"));
        return null;
    }
}
