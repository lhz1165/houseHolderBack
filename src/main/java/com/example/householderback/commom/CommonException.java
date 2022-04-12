package com.example.householderback.commom;

import com.example.householderback.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@ControllerAdvice
@RestController
public class CommonException {
    @ExceptionHandler(MyException.class)
    public Result<String> exceptionHandler(MyException e) {
        return Result.failed(Result.RetCode.UNAUTHORIZED,e.getMessage(),null);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.failed();
    }

}
