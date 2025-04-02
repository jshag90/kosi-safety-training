package com.kosi.service;

import com.kosi.dao.CourseLectureDao;
import com.kosi.dto.CourseCategoryDto;
import com.kosi.entity.CourseCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseLectureService {

    private final CourseLectureDao courseLectureDao;
    public List<CourseCategoryDto> getCourseCategoryTypeList() {
        return courseLectureDao.getCourseCategory();
    }

}
