package com.acme.learningcenterbacksw55.learning.domain.model.entities;


import com.acme.learningcenterbacksw55.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterbacksw55.learning.domain.model.valueobjects.ProgressStatus;
import com.acme.learningcenterbacksw55.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProgressRecordItem extends AuditableModel {
    @Id
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "enrollment")
    private Enrollment enrollment;

    @Getter
    private Long tutorial;

    private ProgressStatus status;
    private Date startedAt;
    private Date completedAt;

    public ProgressRecordItem() {

    }

    public ProgressRecordItem(Enrollment enrollment, Long tutorial) {
        this.enrollment = enrollment;
        this.tutorial = tutorial;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isComplete() {
        return this.status == ProgressStatus.COMPLETED;
    }

    public boolean isInProgress() {
        return this.status == ProgressStatus.STARTED;
    }

    public boolean isNotStarted() {
        return this.status == ProgressStatus.NOT_STARTED;
    }

    public long calculatedDaysElapsed() {
        if (isNotStarted()) return 0;
        var defaultTimeZone =  java.time.ZoneId.systemDefault();
        var fromDate = this.startedAt.toInstant();
        var toDate = this.completedAt == null ? LocalDate.now().atStartOfDay(defaultTimeZone).toInstant() : this.completedAt.toInstant();
        return  java.time.Duration.between(fromDate, toDate).toDays();
    }
}

