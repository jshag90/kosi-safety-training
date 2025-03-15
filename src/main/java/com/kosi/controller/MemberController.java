package com.kosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 사용자 관리
 * 회원가입
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/sign-up")
    public String signUpPage(){
        return "/member/sign_up";
    };
}
