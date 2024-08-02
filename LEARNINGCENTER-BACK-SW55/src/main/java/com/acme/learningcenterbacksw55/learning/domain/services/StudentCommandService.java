package com.acme.learningcenterbacksw55.learning.domain.services;

import com.acme.learningcenterbacksw55.learning.domain.model.commands.CreateStudentCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.AcmeStudentRecordId;

public interface StudentCommandService {

    AcmeStudentRecordId handle(CreateStudentCommand command);

    AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}
