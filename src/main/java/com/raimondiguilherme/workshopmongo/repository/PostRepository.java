package com.raimondiguilherme.workshopmongo.repository;

import com.raimondiguilherme.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Camada de acesso aos dados (comunica diretamente com o db)
@Repository
public interface PostRepository extends MongoRepository<Post, String> { // segundo parametro: Tipo do ID

    List<Post> findByTitleContainingIgnoreCase(String title);
}
