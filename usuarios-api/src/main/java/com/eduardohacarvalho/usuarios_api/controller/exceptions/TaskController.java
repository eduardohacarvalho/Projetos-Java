package com.eduardohacarvalho.usuarios_api.controller;

import com.eduardohacarvalho.usuarios_api.dto.TaskResponse;
import com.eduardohacarvalho.usuarios_api.model.Task;
import com.eduardohacarvalho.usuarios_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        List<TaskResponse> tasks = taskService.findAll(userDetails.getUsername())
                .stream()
                .map(TaskResponse::new)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(new TaskResponse(taskService.findById(id, userDetails.getUsername())));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> save(@RequestBody @Valid Task task,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TaskResponse(taskService.save(task, userDetails.getUsername())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid Task task,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(new TaskResponse(taskService.update(id, task, userDetails.getUsername())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        taskService.delete(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}