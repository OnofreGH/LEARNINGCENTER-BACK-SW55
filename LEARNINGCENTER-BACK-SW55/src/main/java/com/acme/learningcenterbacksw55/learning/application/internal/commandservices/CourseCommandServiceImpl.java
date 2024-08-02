package com.acme.learningcenterbacksw55.learning.application.internal.commandservices;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Course;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.learningcenterbacksw55.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learningcenterbacksw55.learning.domain.services.CourseCommandService;
import com.acme.learningcenterbacksw55.learning.infraestructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CourseCommandServiceImpl implements CourseCommandService {

    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Long handle(CreateCourseCommand command) {
        if (courseRepository.existsByTitle(command.title())) {
            throw new IllegalArgumentException("Course with same title already exists");
        }
        var course = new Course(command.title(), command.description());
        courseRepository.save(course);
        return course.getId();

    }

    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        if (!courseRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Course does not exist");
        }
        var courseToUpdate = courseRepository.findById(command.id()).get();
        if (courseRepository.existsByTitleAndIdIsNot(command.title(), command.id()))
            throw new IllegalArgumentException("Course with same title already exists.");
        var updateCourse = courseRepository.save(courseToUpdate.updateInforhmation(command.title(),
                command.description()));
        return Optional.of(updateCourse);
    }

    @Override
    public void handle(DeleteCourseCommand command) {
     if (!courseRepository.existsById(command.courseId())) {
         throw new IllegalArgumentException("Course does not exist");
     }
     courseRepository.deleteById(command.courseId());
    }

    @Override
    public void handle(AddTutorialToCourseLearningPathCommand command) {
        if (!courseRepository.existsById(command.courseId())) {
            throw new IllegalArgumentException("Course does not exist");
        }
        courseRepository.findById(command.courseId()).map(course -> {
            course.addTutorialTolearningPath(command.tutorialId());
            courseRepository.save(course);
            return course;
        });
    }
}
