package com.raimondiguilherme.workshopmongo.resource;

import com.raimondiguilherme.workshopmongo.domain.Post;
import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.dto.UserDTO;
import com.raimondiguilherme.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController // recurso rest
@RequestMapping(value="/users") // caminho do endpoint
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping // pra dizer que este metodo vai ser o endpoint rest do caminho /users
    public ResponseEntity<List<UserDTO>> findAll(){ // RE vai encapsular a estrutura necessaria pra retornar resposta http com possiveis cabecalhos, erros

        List<User> users = service.findAllInDatabase();
        List<UserDTO> usersDTO = users.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO); // .ok:   vai instanciar a RE ja com o codigo de resposta http que retornou com sucesso
        // .body: corpo da resposta
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findByIdDTO(@PathVariable String id){ // @PathVariable pra falar que o esse id tem q casar com o id recebido na url
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User obj = service.userFromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // pega endereço do novo objeto que vou inserir
        return ResponseEntity.created(uri).build(); // created: retorna codigo 201 (quando se cria novo recurso)
        // retorna resposta vazia, com cod 201 e com o cabeçalho contendo a localizacao do novo rec criado
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build(); // ao deletar retorna cod 204 (sem conteudo)
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
        User obj = service.userFromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }

}