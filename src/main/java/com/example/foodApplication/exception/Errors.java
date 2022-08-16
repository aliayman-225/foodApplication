package com.example.foodApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //constructor to all attributes
public enum Errors {
    INCORRECT_EMAIL_OR_PASSWORD("0001","Incorrect email or password");

    private final String code;
    private final String message;

}
