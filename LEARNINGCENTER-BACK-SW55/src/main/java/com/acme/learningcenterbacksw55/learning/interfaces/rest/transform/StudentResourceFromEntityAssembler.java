package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Student;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student student) {
        return new StudentResource(student.getStudentRecordId(), student.getProfileId());
    }
}
