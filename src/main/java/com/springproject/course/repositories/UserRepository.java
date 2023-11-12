package com.springproject.course.repositories;

import com.springproject.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //nesse caso não precisamos criar a implementação da interface, pois o spring data jpa
    //já tem uma implementação padrão
    //vamos criar uma classe de configuração, é uma classe auxiliar que faz
    //algumas configurações para a aplicação
}
