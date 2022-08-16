package com.example.foodApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // getter+setter+NoargsConstructor
@AllArgsConstructor //constructor to all attributes
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;

}
