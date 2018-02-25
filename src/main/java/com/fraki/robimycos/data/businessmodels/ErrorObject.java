package com.fraki.robimycos.data.businessmodels;

/**
 * Created by bambo on 09.10.2017.
 */
public class ErrorObject {

    private int code;
    private String message;

    public ErrorObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
