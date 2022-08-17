package com.example.foodApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * enum for all types of error
 */
public enum Errors {
    REGISTER_WITH_TAKEN_EMAIL("0001","This Email was already taken by another user"),
    INCORRECT_EMAIL_OR_PASSWORD("0002","Incorrect email or password"),
    INVALID_TOKEN("0003","Your session may be expired or invalid please log in again"),
    INVALID_EMAIL_STRUCTURE("0004","Invalid Email Structure, make sure that your Email follows this formula username@domain.com" ),
    INVALID_PASSWORD_STRUCTURE("0005","Your password must be have at least : 8 characters long,1 uppercase & 1 lowercase character 1 number");


    private final String code;
    private final String message;

}
