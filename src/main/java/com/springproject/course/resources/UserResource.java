package com.springproject.course.resources;

import com.springproject.course.entities.User;
import com.springproject.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping //aqui não foi especificado o path pois o UserResource já tem como RequestMapping /users
    //ou seja, por padrão, ao entrar no /users já chamará esse método
    public ResponseEntity<List<User>> findAll() {
        //ResponseEntity é um tipo especifico do spring para
        // retornar respostas de requisições web
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") //isso quer dizer que a requisição vai aceitar uma variavel id na url
    public ResponseEntity<User> findById(@PathVariable Long id) {
        //o @PathVariable diz para o spring que esse id virá do path(url)

        User obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }
}
