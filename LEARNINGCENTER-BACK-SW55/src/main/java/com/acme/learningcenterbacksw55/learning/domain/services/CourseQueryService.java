package com.acme.learningcenterbacksw55.learning.domain.services;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Course;
import com.acme.learningcenterbacksw55.learning.domain.model.entities.LearningPathItem;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.learningcenterbacksw55.learning.domain.model.queries.GetCourseLearningPathItemByCourseIdAndTutorialIdQuery;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {

    Optional<Course> handle(GetCourseByIdQuery query);

    List<Course> handle(GetAllCoursesQuery query);

    Optional<LearningPathItem> handle(GetCourseLearningPathItemByCourseIdAndTutorialIdQuery query);
}
