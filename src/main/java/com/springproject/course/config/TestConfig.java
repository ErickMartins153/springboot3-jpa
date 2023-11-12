package com.springproject.course.config;

import com.springproject.course.entities.User;
import com.springproject.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test") // para setar quem vai ser o profile
public class TestConfig implements CommandLineRunner {
    //é uma config especifica para o perfil Test
    //para fazer um objeto depender de outro declaramos
    //a dependencia:

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        //tudo dentro desse método será executado quando a aplicação
        //for iniciada
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
