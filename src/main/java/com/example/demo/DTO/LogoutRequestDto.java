package com.example.demo.DTO;

import com.example.demo.Model.Token;

public class LogoutRequestDto {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
