package com.kosi.service;

import com.kosi.dao.CourseLectureDao;
import com.kosi.dto.CourseCategoryDto;
import com.kosi.dto.CourseDto;
import com.kosi.util.CourseCategory;
import com.kosi.util.CourseCategoryType;
import com.kosi.util.CourseStatus;
import com.kosi.util.DateUtil;
import com.kosi.vo.CourseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
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
    public List<CourseDto> getCourseList(Integer pageSize, Integer page, CourseCategoryType courseCategoryType, CourseCategory courseCategory) {
        List<CourseDto> courseList = courseLectureDao.getCourseList(pageSize, page, courseCategoryType, courseCategory);
        List<CourseDto> handleCourseList = new ArrayList<>();

        for (CourseDto courseDto : courseList) {
            Long courseId = courseDto.getCourseId();

            Integer currentApplyCount = courseLectureDao.getApplyEnrollmentCountByCourseId(courseId).intValue();
            int currentEnrollment = currentApplyCount + courseDto.getWrittenApplicationCount(); //신청 사용자 + 서명 등록 사용자 수

            CourseDto handleCourseDto = CourseDto.builder()
                    .courseId(courseId)
                    .title(courseDto.getTitle())
                    .courseStartDate(courseDto.getCourseStartDate())
                    .courseEndDate(courseDto.getCourseEndDate())
                    .applyStartDate(courseDto.getApplyStartDate())
                    .applyEndDate(courseDto.getApplyEndDate())
                    .courseTimeSum(getCourseTimeSumByCourseDto(courseDto))
                    .courseQuestion(courseQuestion)
                    .maxCapacity(courseDto.getMaxCapacity())
                    .currentEnrollment(currentEnrollment)
                    .writtenApplicationCount(courseDto.getWrittenApplicationCount())
                    .location(courseDto.getLocation())
                    .price(courseDto.getPrice())
                    .courseStatus(getCourseStatusByCourseDto(currentEnrollment, courseDto))
                    .courseCategoryId(courseDto.getCourseCategoryId())
                    .courseThumbnailBase64(courseLectureDao.getCourseThumbnailByCourseId(courseId))
                    .build();

            handleCourseList.add(handleCourseDto);
        }

        return handleCourseList;
    }

    private String getCourseStatusByCourseDto(int currentEnrollment, CourseDto courseDto) {

        if(courseDto.getMaxCapacity() <= currentEnrollment){
            return CourseStatus.OVER_PERSON_COUNT.name();
        }

        if (LocalDate.now().isBefore(courseDto.getApplyStartDate())
                || LocalDate.now().isAfter(courseDto.getApplyEndDate())) {
            return CourseStatus.INVALID_APPLY_DATE.name();
        }

        return CourseStatus.OK_APPLY.name();
    }

    private String getCourseTimeSumByCourseDto(CourseDto courseDto) {
        int diffDayStartDateAndEndDate = DateUtil.getDiffDayStartDateAndEndDate(courseDto.getCourseStartDate(), courseDto.getCourseEndDate());
        int diffTimeStartTimeAndEndTime = DateUtil.getDiffTimeStartTimeAndEndTime(courseDto.getCourseStartTime(), courseDto.getCourseEndTime());

        int totalMinute = (diffDayStartDateAndEndDate + 1) * diffTimeStartTimeAndEndTime;
        return DateUtil.formatMinutesToDaysHoursMinutes(totalMinute);
    }
}
