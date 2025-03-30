package com.kosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 마이페이지 화면
 * 내 강의보기
 * 수강신청내역
 * 수료증발급
 * 개인정보수정
 */
@Controller
@RequestMapping("/my-page")
public class MyPageController {

    //개인정보 수정
    @GetMapping("/modify-account")
    public String modifyAccountPage(){
        return "/mypage/account/modify_account";
    };
    
}
