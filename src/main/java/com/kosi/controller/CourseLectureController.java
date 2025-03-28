package com.kosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course-lecture")
public class CourseLectureController {

    //집체교육 소개
    @GetMapping("/group-education/introduction")
    public String groupEducationIntroductionPage(){
        return "/course_lecture/group_education/introduction";
    }

    @GetMapping("/course/save")
    public String courseSavePage(){
        return "/mypage/course/save";
    }
}
