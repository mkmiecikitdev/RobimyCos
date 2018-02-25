package com.fraki.robimycos.exceptions;

/**
 * Created by bambo on 10.10.2017.
 */
public class UserWithIdNotFoundException extends RuntimeException {
    public UserWithIdNotFoundException(long id) {
        super("4User with id: " + String.valueOf(id) + " not found.");
    }
}
