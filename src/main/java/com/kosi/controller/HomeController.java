package com.kosi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        // SecurityContext에서 인증된 사용자 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증된 사용자 정보를 모델에 추가합니다.
        model.addAttribute("username", authentication.getName());
        model.addAttribute("message", "Hello, Spring MVC!");
        return "home";
    }
}

