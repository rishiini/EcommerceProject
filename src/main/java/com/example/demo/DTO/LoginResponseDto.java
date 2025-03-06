package com.example.demo.DTO;

import com.example.demo.Model.Token;
import com.example.demo.Model.User;

public class LoginResponseDto {
    private Token token;
    private ResponseStatus responseStatus;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
