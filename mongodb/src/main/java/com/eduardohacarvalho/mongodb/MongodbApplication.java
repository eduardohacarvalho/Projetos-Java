package com.eduardohacarvalho.mongodb;

import com.eduardohacarvalho.mongodb.domain.User;
import com.eduardohacarvalho.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
