package com.raimondiguilherme.workshopmongo.resource;

import com.raimondiguilherme.workshopmongo.domain.Post;
import com.raimondiguilherme.workshopmongo.resource.util.URL;
import com.raimondiguilherme.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

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

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title){
        title = URL.decode(title);
        List<Post> list = postService.findByTitle(title);
        return ResponseEntity.ok().body(list);
    }

}