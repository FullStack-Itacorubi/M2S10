package com.example.security.controller;

import com.example.security.dto.LoginDto;
import com.example.security.security.jwt.JwtProvider;
import com.example.security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService service;

    public UsersController (UsersService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return this.service.login(loginDto);
    }

}
