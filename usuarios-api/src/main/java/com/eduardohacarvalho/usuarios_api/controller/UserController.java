package com.eduardohacarvalho.usuarios_api.controller;

import com.eduardohacarvalho.usuarios_api.dto.UserResponse;
import com.eduardohacarvalho.usuarios_api.model.User;
import com.eduardohacarvalho.usuarios_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = userService.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserResponse(userService.findById(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(new UserResponse(userService.findByEmail(email)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(userService.save(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid User user) {
        return ResponseEntity.ok(new UserResponse(userService.update(id, user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}