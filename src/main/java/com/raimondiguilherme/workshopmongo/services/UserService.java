package com.raimondiguilherme.workshopmongo.services;

import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.dto.UserDTO;
import com.raimondiguilherme.workshopmongo.repository.UserRepository;
import com.raimondiguilherme.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired // Mecanismo de injeçao de dependencias automatica: pra instanciar automaticamente um objeto
    private UserRepository repo; // Quando vc declara um objeto dentro de uma classe usando o @Autowired, o proprio...
                                 // ... spring vai procurar a definicao do objeto e automaticamente instancia-lo

    public List<User> findAllInDatabase(){
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public void delete(String id){
        repo.deleteById(id);
    }

    public User userFromDto(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
