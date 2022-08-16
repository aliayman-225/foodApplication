package com.example.foodApplication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleResourceNotFound(final EmployeeNotFoundException exception, final HttpServletRequest request){

        return new ErrorResponse(Errors.INCORRECT_EMAIL_OR_PASSWORD.getMessage(), Errors.INCORRECT_EMAIL_OR_PASSWORD.getCode());
    }
}
