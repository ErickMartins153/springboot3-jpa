package com.springproject.course.resources;

import com.springproject.course.entities.User;
import com.springproject.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        //pra dizer que o obj vai chegar como JSON e precisará ser deserializado para um obj User do java
        //precisamos usar a annotation @RequestBody
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
        //quando usamos o .ok() retornamos o valor 200 OK
        //mas para inserção em um DB, o correto seria retornar 201
        //para fazer isso precisamos usar o método created() que espera um URI
        //para gerar essa location URI faremos a seguinte uri
        // o objeto URI pega a location do request atual, pelo path usando o id do objeto que acabou de ser inserido
        //no insomnia assim que fazemos o post, ao ir em headers vemos o Location com o value sendo o caminho
        // do recurso
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        //void pois a resposta dessa requisição não retorna nenhum Body
        service.delete(id);
        return ResponseEntity.noContent().build();
        //noContent retorna uma resposta vazia com código 204, que representa a ausência de conteudo
        //se tentarmos, por exemplo, deletar o usuário 1 receberemos o código 500, e isso acontece
        //pois o usuário id 1 possui pedidos associados à ele, e isso quebraria a integridade do DB
        //depois trataremos isso
    }

}
