package com.kosi.controller;

import com.kosi.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 회원가입 API
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        authService.registerUser(username, password);
        return "User registered successfully";
    }

    // 로그인 API
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authService.loginUser(username, password);
    }
}
