package com.springsimplespasos.universidad.universidadbackend.exception;

public class BadRequestExecption extends RuntimeException{

    public BadRequestExecption(String message) {
        super(message);
    }
}
