package com.eduardohacarvalho.mongodb.config;

import com.eduardohacarvalho.mongodb.domain.Post;
import com.eduardohacarvalho.mongodb.domain.User;
import com.eduardohacarvalho.mongodb.dto.AuthorDTO;
import com.eduardohacarvalho.mongodb.repository.PostRepository;
import com.eduardohacarvalho.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import static java.util.logging.Level.parse;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        postRepository.deleteAll();
        Post post1 = new Post (null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para SP. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post (null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
