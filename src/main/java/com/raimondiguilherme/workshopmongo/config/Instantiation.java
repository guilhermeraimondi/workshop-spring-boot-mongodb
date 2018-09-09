package com.raimondiguilherme.workshopmongo.config;

import com.raimondiguilherme.workshopmongo.domain.Post;
import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.dto.AuthorDTO;
import com.raimondiguilherme.workshopmongo.dto.CommentDTO;
import com.raimondiguilherme.workshopmongo.repository.PostRepository;
import com.raimondiguilherme.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner { // instanciar todos os tuplas/elem do banco de dados

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        // eh necessario persistir os objetos User antes de relacionar, pois se nao o db nao tera criado o id para eles e nos post author id estara null

        Post post1 = new Post(null, sdf.parse("11/03/2018"), "Partiu viagem!", "Vou viajar para SP, abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia familia", "Acordei disposta hoje", new AuthorDTO(maria));
        CommentDTO c1 = new CommentDTO("Boa viagem!!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite, bjs!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);

    }
}
