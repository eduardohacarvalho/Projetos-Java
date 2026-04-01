package com.jpa_spring.jpa_spring.services;

import com.jpa_spring.jpa_spring.entities.User;
import com.jpa_spring.jpa_spring.repositories.UserRepository;
import com.jpa_spring.jpa_spring.services.exceptions.DataBaseException;
import com.jpa_spring.jpa_spring.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Integer id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));

    }

    public User insert(User user){
        return repository.save(user);
    }

    public void delete(Integer id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        } else {
            try{
                repository.deleteById(id);
            } catch (DataIntegrityViolationException e){
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public User update(Integer id, User user){
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, user);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
