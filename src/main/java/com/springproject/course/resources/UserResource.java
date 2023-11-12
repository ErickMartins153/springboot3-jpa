package com.springproject.course.resources;

import com.springproject.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {



    @GetMapping
    public ResponseEntity<User> findAll() {
        //ResponseEntity é um tipo especifico do spring para
        // retornar respostas de requisições web
        User user = new User(1L, "Maria", "maria@gmail.com", "99999", "12345");

        return ResponseEntity.ok().body(user);
    }

}
