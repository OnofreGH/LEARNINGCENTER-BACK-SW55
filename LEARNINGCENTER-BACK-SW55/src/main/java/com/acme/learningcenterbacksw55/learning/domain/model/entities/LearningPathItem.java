package com.acme.learningcenterbacksw55.learning.domain.model.entities;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Course;
import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.TutorialId;
import com.acme.learningcenterbacksw55.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class LearningPathItem extends AuditableModel {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @NotNull
    private Long tutorialId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearningPathItem nextItem;


    public LearningPathItem() {
        this.tutorialId = 0L;
        this.nextItem = null;
    }

    public LearningPathItem(Course course, Long tutorialId, LearningPathItem nextItem) {
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }

    public void updateNextItem(LearningPathItem nextItem) {
        this.nextItem = nextItem;
    }


}
