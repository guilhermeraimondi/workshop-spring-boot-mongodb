package com.raimondiguilherme.workshopmongo.resource;

import com.raimondiguilherme.workshopmongo.domain.Post;
import com.raimondiguilherme.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // recurso rest
@RequestMapping(value="/posts") // caminho do endpoint
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findByIdDTO(@PathVariable String id){ // @PathVariable pra falar que o esse id tem q casar com o id recebido na url
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}