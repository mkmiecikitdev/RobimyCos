package com.fraki.robimycos.exceptions;

/**
 * Created by bambo on 10.10.2017.
 */
public class UserWithLoginNotFoundException extends RuntimeException {
    public UserWithLoginNotFoundException(String login) {
        super("5User with login: " + login + " not found.");
    }
}
