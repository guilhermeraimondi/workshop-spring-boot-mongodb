package com.raimondiguilherme.workshopmongo.resource.exceptions;

import com.raimondiguilherme.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // responsavel por tratar possiveis erros nas requisiçoes
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(),
                "Nao achou a pessoa q c procurou vei", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status((status)).body(err);
    }
}
