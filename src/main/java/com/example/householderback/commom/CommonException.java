package com.example.householderback.commom;

import com.example.householderback.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@ControllerAdvice
@RestController
public class CommonException {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.failed();
    }

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Result<Void> businessInterfaceException(MyException e, HttpServletRequest request) {
        return Result.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> businessInterfaceException(RuntimeException e, HttpServletRequest request) {
        e.printStackTrace();
        return Result.failed();
    }



}
