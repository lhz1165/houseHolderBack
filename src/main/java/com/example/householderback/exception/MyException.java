package com.example.householderback.exception;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public class MyException extends RuntimeException{
    private String message;
    private String code;

    public MyException(String message) {
        this.message = message;
    }

    public MyException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public MyException(String message, String message1, String code) {
        super(message);
        this.message = message1;
        this.code = code;
    }

    public MyException(String message, Throwable cause, String message1, String code) {
        super(message, cause);
        this.message = message1;
        this.code = code;
    }

    public MyException(Throwable cause, String message, String code) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
    }



    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
