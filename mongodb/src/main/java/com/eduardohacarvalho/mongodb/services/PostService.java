package com.eduardohacarvalho.mongodb.services;

import com.eduardohacarvalho.mongodb.domain.Post;
import com.eduardohacarvalho.mongodb.repository.PostRepository;
import com.eduardohacarvalho.mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
