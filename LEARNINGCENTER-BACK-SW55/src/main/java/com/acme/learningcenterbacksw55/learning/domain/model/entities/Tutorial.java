package com.acme.learningcenterbacksw55.learning.domain.model.entities;

import com.acme.learningcenterbacksw55.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Tutorial extends AuditableModel {

    @Getter
    @Id
    private Long id;

    private String title;
    private String description;
    private String contentUrl;
}
