package com.example.demo.Controller;

import com.example.demo.DTO.*;
import com.example.demo.DTO.ResponseStatus;
import com.example.demo.Model.Token;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto login (@RequestBody LoginRequestDto loginRequestDto){
        Token login = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(login);
        loginResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return loginResponseDto;
    }

    @PostMapping("signUp")
    public SignupResponseDto signUp(@RequestBody SignupRequestDto signupRequestDto){
        User user = userService.signUp(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        SignupResponseDto responseDto = new SignupResponseDto();
        responseDto.setEmail(user.getEmail());
        responseDto.setName(user.getName());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }

    @PostMapping("/validate")
    public UserDto validateToken(@RequestHeader("Authorization") String token){
        User user = userService.validateToken(token);
        return UserDto.fromUser(user);
    }

    public void logout(@RequestBody LogoutRequestDto logoutRequestDto){

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutRequestDto logoutRequestDto){
        userService.logout(logoutRequestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
