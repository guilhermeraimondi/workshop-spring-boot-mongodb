package com.raimondiguilherme.workshopmongo.repository;

import com.raimondiguilherme.workshopmongo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
// Camada de acesso aos dados (comunica diretamente com o db)
@Repository
public interface UserRepository extends MongoRepository<User, String> { // segundo parametro: Tipo do ID
}
