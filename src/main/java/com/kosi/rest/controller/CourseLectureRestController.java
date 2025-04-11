package com.kosi.rest.controller;

import com.kosi.dto.*;
import com.kosi.service.CourseLectureService;
import com.kosi.util.CourseCategory;
import com.kosi.util.CourseCategoryType;
import com.kosi.util.ErrorCode;
import com.kosi.vo.CourseVO;
import com.kosi.vo.LectureVO;
import com.kosi.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/course-lecture")
@RequiredArgsConstructor
@Slf4j
public class CourseLectureRestController {

    private final CourseLectureService courseLectureService;

    @GetMapping("/course-category")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResultVO<List<CourseCategoryDto>>> getCourseCategoryTypes() {
        ResultVO<List<CourseCategoryDto>> resultVO = ResultVO.<List<CourseCategoryDto>>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(courseLectureService.getCourseCategoryTypeList())
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/course/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResultVO<Void>> saveCourse(@ModelAttribute CourseVO.RequestSaveVO requestSaveVO) throws IOException {

        log.info(requestSaveVO.toString());

        courseLectureService.saveCourse(requestSaveVO);

        ResultVO<Void> resultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();

        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/course/list")
    public ResponseEntity<ResultVO<List<CourseDto>>> getCourseList(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page,
            @RequestParam("courseCategoryType") CourseCategoryType courseCategoryType,
            @RequestParam("courseCategory") CourseCategory courseCategory
    ) {

        List<CourseDto> courseDtoList = courseLectureService.getCourseList(pageSize, page, courseCategoryType, courseCategory);
        ResultVO<List<CourseDto>> resultVO = ResultVO.<List<CourseDto>>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(courseDtoList)
                .build();

        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<ResultVO<ListResp<CourseDto>>> getCourses(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page
    ) {

        ListResp<CourseDto> courseDtoList = courseLectureService.getCourses(pageSize, page);
        ResultVO<ListResp<CourseDto>> resultVO = ResultVO.<ListResp<CourseDto>>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(courseDtoList)
                .build();

        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/courses")
    public ResponseEntity<ResultVO<Void>> deleteCourseById(@RequestParam("courseId") Long courseId) {
        courseLectureService.deleteCourseById(courseId);
        ResultVO<Void> resultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<ResultVO<CourseDto>> getCourse(
            @RequestParam("courseId") Long courseId
    ) {

        CourseDto courseDto = courseLectureService.getCourseById(courseId);
        ResultVO<CourseDto> courseDtoResultVO = ResultVO.<CourseDto>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(courseDto)
                .build();

        return new ResponseEntity<>(courseDtoResultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/course")
    public ResponseEntity<ResultVO<Void>> updateCourse(@ModelAttribute CourseVO.RequestUpdateVO requestUpdateVO) throws IOException {
        log.info(requestUpdateVO.toString());
        courseLectureService.updateCourse(requestUpdateVO);

        ResultVO<Void> voidResultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();

        return new ResponseEntity<>(voidResultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/lectures")
    public ResponseEntity<ResultVO<ListResp<LectureDto>>> getLectures(
            @RequestParam("courseId") Long courseId,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("page") Integer page
    ) {

        ListResp<LectureDto> lectureDtoList = courseLectureService.getLectures(courseId, pageSize, page);
        ResultVO<ListResp<LectureDto>> resultVO = ResultVO.<ListResp<LectureDto>>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(lectureDtoList)
                .build();

        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/lecture/update-order")
    public ResponseEntity<ResultVO<Void>> updateLectureOrder(
            @RequestParam("courseId") Long courseId,
            @RequestBody List<LectureVO.OrderVO> lectureOrderVOList) {
        log.info(lectureOrderVOList.toString());
        courseLectureService.updateLectureOrder(courseId, lectureOrderVOList);

        ResultVO<Void> resultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();

        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/lecture")
    public ResponseEntity<ResultVO<Void>> saveLecture(@ModelAttribute LectureVO.RequestSave requestSave) {

        courseLectureService.saveLecture(requestSave);

        ResultVO<Void> resultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();

        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }


}
