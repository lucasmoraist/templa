package com.lucasmoraist.templa.domain.exception;

public class GroupFullException extends RuntimeException {

    public GroupFullException() {
        super("Group is full");
    }

}
