package com.raimondiguilherme.workshopmongo.services;

import com.raimondiguilherme.workshopmongo.domain.Post;
import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.repository.PostRepository;
import com.raimondiguilherme.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired // Mecanismo de injeçao de dependencias automatica: pra instanciar automaticamente um objeto
    private PostRepository postRepository; // Quando vc declara um objeto dentro de uma classe usando o @Autowired, o proprio...
                                 // ... spring vai procurar a definicao do objeto e automaticamente instancia-lo

    public List<Post> findAllInDatabase(){
        return postRepository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
