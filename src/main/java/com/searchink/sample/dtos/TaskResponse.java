package com.searchink.sample.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searchink.sample.domain.TaskStatus;

import java.util.Date;
import java.util.UUID;

public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private Date dueAt;
    private int priority;
    private TaskStatus status;

    public TaskResponse(UUID id, String title, String description, Date dueAt, int priority, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueAt = dueAt;
        this.priority = priority;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getDueAt() {
        return dueAt;
    }

    public int getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
