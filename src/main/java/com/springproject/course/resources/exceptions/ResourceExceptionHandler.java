package com.springproject.course.resources.exceptions;

import com.springproject.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice //essa annotation intercepta as exceções que acontecem para que esse objeto possa
//executar um possivel tratamento
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//nome da exceção a ser tratada aqui
    //annotation para que o método seja capaz de interceptar a requisição que deu exceção para cair aqui
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; //assim será 404
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }
}
