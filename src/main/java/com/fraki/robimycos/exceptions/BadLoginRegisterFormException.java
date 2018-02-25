package com.fraki.robimycos.exceptions;

/**
 * Created by bambo on 09.10.2017.
 */
public class BadLoginRegisterFormException extends RuntimeException {

    public BadLoginRegisterFormException() {
        super("1Invalid form data");
    }
}
