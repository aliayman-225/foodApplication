package com.example.foodApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //constructor to all attributes
public enum Errors {
    REGISTER_WITH_TAKEN_EMAIL("0001","This Email was already taken by another user"),
    INCORRECT_EMAIL_OR_PASSWORD("0002","Incorrect email or password"),
    INVALID_TOKEN("0003","Your session may be expired or invalid please log in again"),
    INVALID_EMAIL_STRUCTURE("0004","Invalid Email Structure, make sure that your Email follows this formula username@domain.com" );

    private final String code;
    private final String message;

}
