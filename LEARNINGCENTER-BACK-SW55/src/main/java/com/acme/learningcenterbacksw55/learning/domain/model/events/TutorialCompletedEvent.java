package com.acme.learningcenterbacksw55.learning.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * TutorialCompletedEvent
 * <p>
 *     This event is fired when a tutorial es completed
 * </p>
 */
@Getter
public final class TutorialCompletedEvent extends ApplicationEvent {

    private final Long enrollmentId;
    private final Long tutorialId;

    public TutorialCompletedEvent(Object source, Long enrollmentId, Long tutorialId) {
        super(source);
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
    }
}
