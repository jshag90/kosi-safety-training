package com.kosi.service;

import com.kosi.dao.CourseLectureDao;
import com.kosi.dto.CourseCategoryDto;
import com.kosi.dto.CourseDto;
import com.kosi.vo.CourseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseLectureService {

    private final static String courseQuestion = "(033) 645-6330";
    private final CourseLectureDao courseLectureDao;

    public List<CourseCategoryDto> getCourseCategoryTypeList() {
        return courseLectureDao.getCourseCategory();
    }

    @Transactional
    public void saveCourse(CourseVO.RequestSaveVO requestSaveVO) throws IOException {
        courseLectureDao.saveCourse(requestSaveVO);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getCourseList(Integer pageSize, Integer page) {
        List<CourseDto> courseList = courseLectureDao.getCourseList(pageSize, page);
        List<CourseDto> handleCourseList = new ArrayList<>();

        for (CourseDto courseDto : courseList) {
            //TODO 현재 사용자 신청 인원 count 가져오기
            Integer currentApplyCount = 0;
            int currentEnrollment = currentApplyCount + courseDto.getWrittenApplicationCount();

            //TODO 현재 날짜, 인원 통해서 과정 상태 정보 초기화
            String courseStatus = "WAIT";

            //TODO 강의 날짜, 강의 시간 합계 통해서 총 시간 계산하기
            String courseTimeSum = "1일 20시간";
            CourseDto handleCourseDto = CourseDto.builder()
                    .courseId(courseDto.getCourseId())
                    .title(courseDto.getTitle())
                    .courseStartDate(courseDto.getCourseStartDate())
                    .courseEndDate(courseDto.getCourseEndDate())
                    .applyStartDate(courseDto.getApplyStartDate())
                    .applyEndDate(courseDto.getApplyEndDate())
                    .courseTimeSum(courseTimeSum)
                    .courseQuestion(courseQuestion)
                    .maxCapacity(courseDto.getMaxCapacity())
                    .currentEnrollment(currentEnrollment)
                    .writtenApplicationCount(courseDto.getWrittenApplicationCount())
                    .location(courseDto.getLocation())
                    .price(courseDto.getPrice())
                    .courseStatus(courseStatus)
                    .build();

            handleCourseList.add(handleCourseDto);
        }

        return handleCourseList;
    }
}
