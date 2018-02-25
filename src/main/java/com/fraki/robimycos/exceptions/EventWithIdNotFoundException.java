package com.fraki.robimycos.exceptions;

/**
 * Created by bambo on 10.10.2017.
 */
public class EventWithIdNotFoundException extends RuntimeException {
    public EventWithIdNotFoundException(long id) {
        super("Event with id: " + String.valueOf(id) + " not found");
    }
}
