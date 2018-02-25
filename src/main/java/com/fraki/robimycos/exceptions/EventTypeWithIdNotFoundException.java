package com.fraki.robimycos.exceptions;

/**
 * Created by bambo on 10.10.2017.
 */
public class EventTypeWithIdNotFoundException extends RuntimeException {
    public EventTypeWithIdNotFoundException(long typeId) {
        super("6Event type with id: " + String.valueOf(typeId) + " not found.");
    }
}
