package com.eduardohacarvalho.usuarios_api.service;

import com.eduardohacarvalho.usuarios_api.model.User;
import com.eduardohacarvalho.usuarios_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public User save(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email já cadastrado");
        });
        return userRepository.save(user);
    }

    public User update(Long id, User userAtualizado) {
        User user = findById(id);
        user.setName(userAtualizado.getName());
        user.setEmail(userAtualizado.getEmail());
        user.setPassword(userAtualizado.getPassword());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}