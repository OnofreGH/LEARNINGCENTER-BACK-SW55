package com.acme.learningcenterbacksw55.learning.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.learning.domain.model.entities.LearningPathItem;
import com.acme.learningcenterbacksw55.learning.interfaces.rest.resources.LearningPathItemResource;

public class LearningPathItemResourceFromEntityAssembler {
    public static LearningPathItemResource toResourceFromEntity(LearningPathItem learningPathItem) {
        return new LearningPathItemResource(learningPathItem.getId(),
                learningPathItem.getCourse().getId(),
                learningPathItem.getTutorialId());
    }
}
