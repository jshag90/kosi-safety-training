package com.kosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course-lecture")
public class CourseLectureController {

    @GetMapping("/course/save")
    public String courseSavePage(){
        return "/mypage/course/save";
    }
}
