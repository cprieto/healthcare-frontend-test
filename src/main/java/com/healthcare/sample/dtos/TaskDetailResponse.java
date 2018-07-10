package com.healthcare.sample.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class TaskDetailResponse {
    private UUID id;
    private Date createdAt;
    private String title;
    private String description;
    private Date dueAt;
    private int priority;

    public UUID getId() {
        return id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreatedAt() {
        return createdAt;
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

    public TaskDetailResponse(UUID id, Date createdAt, String title, String description, Date dueDate, int priority) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.dueAt = dueDate;
        this.priority = priority;
    }
}
