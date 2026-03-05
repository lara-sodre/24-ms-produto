package com.github.cidarosa.ms.produto.exeptions;

public class ResourceNotFoundExeption extends RuntimeException {
    public ResourceNotFoundExeption(String message) {
        super(message);
    }
}
