package com.eduardohacarvalho.mongodb.resources;

import com.eduardohacarvalho.mongodb.domain.User;
import com.eduardohacarvalho.mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<User>> findAll () {
        List<User> list = service.findAll();
        System.out.println("DEBUG: Quantidade de usuários no banco: " + list.size());
        return ResponseEntity.ok().body(list);
    }
}
