package com.raimondiguilherme.workshopmongo.services;

import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired // Mecanismo de injeçao de dependencias automatica: pra instanciar automaticamente um objeto
    private UserRepository repo; // Quando vc declara um objeto dentro de uma classe usando o @Autowired, o proprio...
                                 // ... spring vai procurar a definicao do objeto e automaticamente instancia-lo

    public List<User> findAllInDatabase(){
        return repo.findAll();
    }
}
