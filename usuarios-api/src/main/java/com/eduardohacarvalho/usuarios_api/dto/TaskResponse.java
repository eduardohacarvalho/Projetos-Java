package com.eduardohacarvalho.usuarios_api.dto;

import com.eduardohacarvalho.usuarios_api.model.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDateTime createdAt;
    private String userEmail;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.completed = task.getCompleted();
        this.createdAt = task.getCreatedAt();
        this.userEmail = task.getUser().getEmail();
    }
}