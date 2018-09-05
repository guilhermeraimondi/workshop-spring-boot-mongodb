package com.raimondiguilherme.workshopmongo.resource;

import com.raimondiguilherme.workshopmongo.domain.User;
import com.raimondiguilherme.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController // recurso rest
@RequestMapping(value="/users") // caminho do endpoint
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET) // pra dizer que este metodo vai ser o endpoint rest do caminho /users
    public ResponseEntity<List<User>> findAll(){ // RE vai encapsular a estrutura necessaria pra retornar resposta http com possiveis cabecalhos, erros

        List<User> users = service.findAllInDatabase();
        return ResponseEntity.ok().body(users); // .ok:   vai instanciar a RE ja com o codigo de resposta http que retornou com sucesso
                                                // .body: corpo da resposta
    }

}