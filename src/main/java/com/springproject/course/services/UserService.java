package com.springproject.course.services;

import com.springproject.course.entities.User;
import com.springproject.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //colocamos o @Service pois essa classe é uma dependência da UserResource
    @Autowired
    private UserRepository repository;
    //como essa classe tem uma dependência da UserRepository, poderiamos colocar
    //uma annotation de @Repository na interface UserRepository, mas como ela implementa
    //a JPARepository, não é necessário

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get(); //vai retornar o objeto do tipo User que estiver dentro
        //do Optional
    }
}
