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

    //관리감독자 교육(제조업)
    @GetMapping("/group-education/supervisor-training-manufacture")
    public String groupEducationSupervisorTrainingManufacturePage(){
        return "/course_lecture/group_education/supervisor_training_manufacture";
    }

    //관리감독자 교육(기타업)
    @GetMapping("/group-education/supervisor-training-etc")
    public String groupEducationSupervisorTrainingEtcPage(){
        return "/course_lecture/group_education/supervisor_training_etc";
    }
    @GetMapping("/course/save")
    public String courseSavePage(){
        return "/mypage/course/save";
    }
}
