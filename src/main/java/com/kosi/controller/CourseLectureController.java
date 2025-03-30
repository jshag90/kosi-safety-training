package com.kosi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course-lecture")
public class CourseLectureController {

    String groupEducationView = "/course_lecture/group_education";
    String onlineEducationView = "/course_lecture/online_education";

    //집체교육 소개
    @GetMapping("/group-education/introduction")
    public String groupEducationIntroductionPage(){
        return groupEducationView+"/introduction";
    }

    //집체교육 - 관리감독자 교육(제조업)
    @GetMapping("/group-education/supervisor-training-manufacture")
    public String groupEducationSupervisorTrainingManufacturePage(){
        return groupEducationView+"/supervisor_training_manufacture";
    }

    //집체교육 - 관리감독자 교육(기타업)
    @GetMapping("/group-education/supervisor-training-etc")
    public String groupEducationSupervisorTrainingEtcPage(){
        return groupEducationView+"/supervisor_training_etc";
    }

    //온라인교육 소개
    @GetMapping("/online-education/introduction")
    public String onlineEducationIntroductionPage(){
        return onlineEducationView + "/introduction";
    }

    //근로자 정기 교육
    @GetMapping("/online-education/regular-training")
    public String onlineEducationRegularTrainingPage(){
        return onlineEducationView+"/regular_training";
    }

    //채용시 교육
    @GetMapping("/online-education/onboarding-training")
    public String onlineEducationOnboardingTrainingPage(){
        return onlineEducationView + "/onboarding_training";
    }

    //작업 내용 변경시 교육
    @GetMapping("/online-education/change-work-training")
    public String onlineEducationChangeWorkTrainingPage(){
        return onlineEducationView + "/change_work_training";
    }

    //온라인 교육 - 관리감독자 교육(제조업)
    @GetMapping("/online-education/supervisor-training-manufacture")
    public String onlineEducationSupervisorTrainingManufacturePage(){
        return onlineEducationView+"/supervisor_training_manufacture";
    }

    //온라인 교육 - 관리감독자 교육(기타업)
    @GetMapping("/online-education/supervisor-training-etc")
    public String onlineEducationSupervisorTrainingEtcPage(){
        return onlineEducationView+"/supervisor_training_etc";
    }

    @GetMapping("/course/save")
    public String courseSavePage(){
        return "/mypage/course/save";
    }

}
