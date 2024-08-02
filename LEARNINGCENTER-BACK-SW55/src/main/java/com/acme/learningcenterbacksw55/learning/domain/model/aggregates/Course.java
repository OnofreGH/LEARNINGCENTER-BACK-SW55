package com.acme.learningcenterbacksw55.learning.domain.model.aggregates;


import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.LearningPath;
import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.TutorialId;
import com.acme.learningcenterbacksw55.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
public class Course extends AuditableModel {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    /**
     * The learning path for this course
     */

    @Embedded
    private LearningPath learningPath;

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }

    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    /**
     *  Updates the course information
     * @param title The new title
     * @param description The new description
     * @return The update course
     */
    public Course updateInforhmation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    public void addTutorialTolearningPath(Long tutorialId) {
        this.learningPath.addItem(this, tutorialId);

    }
}
