package com.eduardohacarvalho.usuarios_api.service;

import com.eduardohacarvalho.usuarios_api.model.Task;
import com.eduardohacarvalho.usuarios_api.model.User;
import com.eduardohacarvalho.usuarios_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> findAll(String email) {
        User user = (User) userService.loadUserByUsername(email);
        return taskRepository.findByUserId(user.getId());
    }

    public Task findById(Long id, String email) {
        User user = (User) userService.loadUserByUsername(email);
        return taskRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Task save(Task task, String email) {
        User user = (User) userService.loadUserByUsername(email);
        task.setUser(user);
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }
        return taskRepository.save(task);
    }

    public Task update(Long id, Task taskAtualizada, String email) {
        Task task = findById(id, email);
        task.setTitle(taskAtualizada.getTitle());
        task.setDescription(taskAtualizada.getDescription());
        task.setCompleted(taskAtualizada.getCompleted());
        return taskRepository.save(task);
    }

    public void delete(Long id, String email) {
        Task task = findById(id, email);
        taskRepository.delete(task);
    }
}