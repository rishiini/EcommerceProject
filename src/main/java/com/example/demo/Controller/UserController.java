package com.example.demo.Controller;

import com.example.demo.DTO.*;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    public LoginResponseDto login (@RequestBody LoginRequestDto loginRequestDto){
        return null;
    }

    public SignupResponseDto signUp(@RequestBody SignupRequestDto signupRequestDto){
        return null;
    }

    public UserDto validateToken(String token){
        return null;
    }

    public void logout(@RequestBody LogoutRequestDto logoutRequestDto){

    }

    public void logOut(String logout){

    }
}
