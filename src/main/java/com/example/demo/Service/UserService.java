package com.example.demo.Service;

import com.example.demo.Model.Token;
import com.example.demo.Model.User;

public interface UserService {
    Token login(String email, String password);
    User signUp(String name, String email, String password);
    User validateToken(String token);
    void logout(String token);
}
