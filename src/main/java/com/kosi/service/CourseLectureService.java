package com.kosi.service;

import com.kosi.dao.CourseLectureDao;
import com.kosi.dto.CourseCategoryDto;
import com.kosi.vo.CourseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseLectureService {

    private final CourseLectureDao courseLectureDao;
    public List<CourseCategoryDto> getCourseCategoryTypeList() {
        return courseLectureDao.getCourseCategory();
    }

    @Transactional
    public void saveCourse(CourseVO.RequestSaveVO requestSaveVO) throws IOException {
        courseLectureDao.saveCourse(requestSaveVO);
    }
}
