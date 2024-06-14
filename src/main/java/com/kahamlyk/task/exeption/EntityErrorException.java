package com.kahamlyk.task.exeption;

public class EntityErrorException extends RuntimeException{
    public EntityErrorException(String message) {
        super(message);
    }
}
