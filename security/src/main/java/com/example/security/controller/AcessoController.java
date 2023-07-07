package com.example.security.controller;

import com.example.security.dto.LoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acesso")
public class AcessoController {

    @GetMapping
    public String retornaString() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "DEU TUDO CERTO!";
    }

    @GetMapping("/2")
    public String retornaString2() {
        return "DEU TUDO CERTO 2!";
    }

}
