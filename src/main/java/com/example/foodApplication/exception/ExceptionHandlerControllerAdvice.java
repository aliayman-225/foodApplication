package com.example.foodApplication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Controls all the errors as generating the status and the error msg in he body
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleResourceNotFound(final UserNotFoundException exception, final HttpServletRequest request){

        return new ErrorResponse(Errors.INCORRECT_EMAIL_OR_PASSWORD.getMessage(), Errors.INCORRECT_EMAIL_OR_PASSWORD.getCode());
    }


    @ExceptionHandler(TakenEmailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleTakenEmail(final TakenEmailException exception, final HttpServletRequest request){

        return new ErrorResponse(Errors.REGISTER_WITH_TAKEN_EMAIL.getMessage(), Errors.REGISTER_WITH_TAKEN_EMAIL.getCode());
    }



    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleInvalidToken(final InvalidTokenException exception, final HttpServletRequest request){

        return new ErrorResponse(Errors.INVALID_TOKEN.getMessage(), Errors.INVALID_TOKEN.getCode());
    }


    @ExceptionHandler(InvalidEmailStructure.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleInvalidEmailStructure(final InvalidEmailStructure exception, final HttpServletRequest request){

        return new ErrorResponse(Errors.INVALID_EMAIL_STRUCTURE.getMessage(), Errors.INVALID_EMAIL_STRUCTURE.getCode());
    }


    @ExceptionHandler(InvalidPasswordStructure.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleInvalidPasswordStructure(final InvalidPasswordStructure exception, final HttpServletRequest request){

        return new ErrorResponse(Errors.INVALID_PASSWORD_STRUCTURE.getMessage(), Errors.INVALID_PASSWORD_STRUCTURE.getCode());
    }


}

