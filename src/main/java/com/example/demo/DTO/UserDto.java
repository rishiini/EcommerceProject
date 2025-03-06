package com.example.demo.DTO;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;

import java.util.List;

public class UserDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private String email;
    private List<Role> roles;

    public static UserDto fromUser(User user){
        if(user == null){
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
