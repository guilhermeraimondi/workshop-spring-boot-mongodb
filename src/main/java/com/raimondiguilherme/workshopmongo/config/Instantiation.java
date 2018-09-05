package com.raimondiguilherme.workshopmongo.config;

import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class Instantiation implements CommandLineRunner { // instanciar todos os tuplas/elem do banco de dados

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

    }
}
