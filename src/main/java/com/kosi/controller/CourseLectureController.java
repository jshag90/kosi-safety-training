package com.kosi.controller;

import com.kosi.dto.CourseDto;
import com.kosi.service.CourseLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course-lecture")
@Slf4j
@RequiredArgsConstructor
public class CourseLectureController {

    private final CourseLectureService courseLectureService;

    String groupEducationView = "/course_lecture/group_education";
    String onlineEducationView = "/course_lecture/online_education";

    //집체교육 소개
    @GetMapping("/group-education/introduction")
    public String groupEducationIntroductionPage() {
        return groupEducationView + "/introduction";
    }

    //집체교육 - 관리감독자 교육(제조업)
    @GetMapping("/group-education/supervisor-training-manufacture")
    public String groupEducationSupervisorTrainingManufacturePage() {
        return groupEducationView + "/supervisor_training_manufacture";
    }

    @GetMapping("/group-education/supervisor-training-manufacture/{courseId}")
    public String groupEducationSupervisorTrainingManufactureDetailPage(
            @PathVariable("courseId") Long courseId,
            Model model
    ) {
        CourseDto courseDtoByCourseId = courseLectureService.getCourseByCourseId(courseId);
        model.addAttribute("COURSE_DTO", courseDtoByCourseId);
        return groupEducationView + "/supervisor_training_manufacture_view";
    }

    //집체교육 - 관리감독자 교육(기타업)
    @GetMapping("/group-education/supervisor-training-etc")
    public String groupEducationSupervisorTrainingEtcPage() {
        return groupEducationView + "/supervisor_training_etc";
    }

    @GetMapping("/group-education/supervisor-training-etc/{courseId}")
    public String groupEducationSupervisorTrainingEtcDetailViewPage(
            @PathVariable("courseId") Long courseId,
            Model model
    ) {

        CourseDto courseDtoByCourseId = courseLectureService.getCourseByCourseId(courseId);
        model.addAttribute("COURSE_DTO", courseDtoByCourseId);
        return groupEducationView + "/supervisor_training_etc_view";
    }

    //온라인교육 소개
    @GetMapping("/online-education/introduction")
    public String onlineEducationIntroductionPage() {
        return onlineEducationView + "/introduction";
    }

    //근로자 정기 교육
    @GetMapping("/online-education/regular-training")
    public String onlineEducationRegularTrainingPage() {
        return onlineEducationView + "/regular_training";
    }

    //채용시 교육
    @GetMapping("/online-education/onboarding-training")
    public String onlineEducationOnboardingTrainingPage() {
        return onlineEducationView + "/onboarding_training";
    }

    //작업 내용 변경시 교육
    @GetMapping("/online-education/change-work-training")
    public String onlineEducationChangeWorkTrainingPage() {
        return onlineEducationView + "/change_work_training";
    }

    //온라인 교육 - 관리감독자 교육(제조업)
    @GetMapping("/online-education/supervisor-training-manufacture")
    public String onlineEducationSupervisorTrainingManufacturePage() {
        return onlineEducationView + "/supervisor_training_manufacture";
    }

    //온라인 교육 - 관리감독자 교육(기타업)
    @GetMapping("/online-education/supervisor-training-etc")
    public String onlineEducationSupervisorTrainingEtcPage() {
        return onlineEducationView + "/supervisor_training_etc";
    }

    @GetMapping("/course/save")
    public String courseSavePage() {
        return "/mypage/course/save";
    }

    @GetMapping("/course/manage")
    public String courseManagePage() {
        return "/mypage/course/management";
    }

}
