package com.springproject.course.services;

import com.springproject.course.entities.User;
import com.springproject.course.repositories.UserRepository;
import com.springproject.course.services.exceptions.DatabaseException;
import com.springproject.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));//substituimos o .get por esse metodo, que basicamente tenta dar o get
        //mas caso não consiga dá throw nessa exception


        //return obj.get(); //vai retornar o objeto do tipo User que estiver dentro
        //do Optional
    }

    //retorna o usuário salvo
    public User insert(User obj) {
        return repository.save(obj);
        //por padrão o save já retorna o obj salvo
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            //colocamos esse erro mais generico pois qualquer erro de erro de execução
            //será englobada por ela, depois rodamos e vimos qual erro exatamente era retornado
            //ai vimos que é a EmptyResultDataAccessException
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        //getReferenceById instancia o usuário, mas sem ir no DB, ele só deixa o objeto monitorado
        //pelo jpa, pra ser trabalhado e em seguida poder sofrer alguma operação com DB
        //é melhor que o findById pois o findbyid obrigatoriamente vai no DB e pega o objeto
        //o getReference é mais eficiente
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        //atualizar os dados do entity baseado com o que está em obj
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
        //nem todos os campos poderão ser atualizados por aqui, ex: id,senha , atentar a isso!!

    }


}
