package com.eduardohacarvalho.usuarios_api.controller;

import com.eduardohacarvalho.usuarios_api.dto.UserResponse;
import com.eduardohacarvalho.usuarios_api.model.User;
import com.eduardohacarvalho.usuarios_api.security.JwtUtil;
import com.eduardohacarvalho.usuarios_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid User user) {
        return ResponseEntity.ok(new UserResponse(userService.save(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.get("email"),
                        credentials.get("password")
                )
        );
        String token = jwtUtil.generateToken(credentials.get("email"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}