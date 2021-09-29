package com.example.cmdmapi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException() {
        super("Recurso não Encontrado!");
    }
}
