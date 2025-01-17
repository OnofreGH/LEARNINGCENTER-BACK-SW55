package com.acme.learningcenterbacksw55.learning.domain.model.valueobjects;

import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterbacksw55.learning.domain.model.entities.ProgressRecordItem;
import com.acme.learningcenterbacksw55.learning.domain.model.entities.Tutorial;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * ProgressRecord is an entity that is embedded in Enrollment aggregate.
 * It is a value object that is not persisted in the database.
 * It is used to track the progress of a student in a learning path.
 * It is initialized when an enrollment is created.
 * It is updated when a student starts or completes a tutorial.
 */

@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment")
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        Long tutorial = learningPath.getFirstTutorialInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorial);
        progressRecordItems.add(progressRecordItem);
    }

    public void startTutorial(Long tutorialId) {
        if (hasAnItemInProgress()) throw new IllegalStateException("A tutorial is already in progress");

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with given Id is already started o completed");
        }
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");
    }

    public void completeTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complete();
        else throw new IllegalArgumentException("Tutorial with given Id not found in progress record");

        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;
        Long nextTutorial = learningPath.getNextTutorialInLearningPath(tutorialId);
        if (nextTutorial != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorial);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }
    private boolean hasAnItemInProgress() {
        return  progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId) {
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getTutorial().
                equals(tutorialId)).findFirst().orElse(null);
    }

    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream().filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculatedDaysElapsed).sum();
    }
}













