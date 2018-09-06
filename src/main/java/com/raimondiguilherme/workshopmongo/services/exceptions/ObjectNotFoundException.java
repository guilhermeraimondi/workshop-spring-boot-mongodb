package com.raimondiguilherme.workshopmongo.services.exceptions;

public class ObjectNotFoundException extends RuntimeException { //compilador nao exige tratamento

    public ObjectNotFoundException(String s) {
        super(s);
    }
}
