package com.eduardohacarvalho.mongodb.services;

import com.eduardohacarvalho.mongodb.domain.User;
import com.eduardohacarvalho.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return  repo.findAll();
    }
}
