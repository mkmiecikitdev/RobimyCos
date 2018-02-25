package com.fraki.robimycos.exceptionshandler;

import com.fraki.robimycos.data.businessmodels.ErrorObject;
import com.fraki.robimycos.exceptions.BadLoginRegisterFormException;
import com.fraki.robimycos.exceptions.UserWithIdNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bambo on 09.10.2017.
 */
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            BadLoginRegisterFormException.class,
            UserWithIdNotFoundException.class
    })
    protected ResponseEntity<Object> handleCustom(RuntimeException ex, WebRequest request) {
        String message = ex.getMessage();
        int code = Character.getNumericValue(message.charAt(0));
        StringBuilder sb = new StringBuilder(message);
        sb.deleteCharAt(0);

        return handleExceptionInternal(ex, new ErrorObject(code, sb.toString()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {
            DataIntegrityViolationException.class
    })
    protected ResponseEntity<Object> handleLoginExist(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorObject(2, "Login exist"),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }


        return handleExceptionInternal(ex, new ErrorObject(3, "Invalid form"),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
