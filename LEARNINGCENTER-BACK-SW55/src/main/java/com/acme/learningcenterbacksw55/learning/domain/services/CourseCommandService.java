package com.acme.learningcenterbacksw55.learning.domain.services;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Course;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

public interface CourseCommandService {
    Long handle(CreateCourseCommand command);

    Optional<Course> handle(UpdateCourseCommand command);

    void handle(DeleteCourseCommand command);

    void handle(AddTutorialToCourseLearningPathCommand command);
}
