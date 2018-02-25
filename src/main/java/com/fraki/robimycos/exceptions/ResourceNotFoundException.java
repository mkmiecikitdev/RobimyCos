package com.fraki.robimycos.exceptions;

import org.omg.SendingContext.RunTime;

/**
 * Created by bambo on 14.10.2017.
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String what, long id) {
        super("6" + what + " with id: " + String.valueOf(id) + " not found.");
    }
}
