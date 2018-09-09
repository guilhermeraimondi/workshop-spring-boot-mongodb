package com.raimondiguilherme.workshopmongo.repository;

import com.raimondiguilherme.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Camada de acesso aos dados (comunica diretamente com o db)
@Repository
public interface PostRepository extends MongoRepository<Post, String> { // segundo parametro: Tipo do ID

    List<Post> findByTitleContainingIgnoreCase(String title);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }") // ?0 : aproveitar o primeiro paramentro
    List<Post> searchTitle(String title);
}
