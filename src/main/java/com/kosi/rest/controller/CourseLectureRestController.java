package com.kosi.rest.controller;

import com.kosi.dto.CourseCategoryDto;
import com.kosi.dto.CourseDto;
import com.kosi.service.CourseLectureService;
import com.kosi.util.CourseCategory;
import com.kosi.util.CourseCategoryType;
import com.kosi.util.ErrorCode;
import com.kosi.vo.CourseVO;
import com.kosi.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


}
