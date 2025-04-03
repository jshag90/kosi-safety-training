package com.kosi.dao;

import com.kosi.dto.CourseCategoryDto;
import com.kosi.dto.CourseDto;
import com.kosi.entity.Course;
import com.kosi.entity.UploadFiles;
import com.kosi.util.CourseCategory;
import com.kosi.util.CourseCategoryType;
import com.kosi.util.UploadFileType;
import com.kosi.vo.CourseVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.kosi.entity.QCourseCategory.courseCategory;
import static com.kosi.entity.QCourse.course;

@Repository
@RequiredArgsConstructor
public class CourseLectureDao {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public List<CourseCategoryDto> getCourseCategory() {
        return jpaQueryFactory.select(
                Projections.bean(CourseCategoryDto.class,
                        courseCategory.courseCategoryId.as("id")
                        , courseCategory.courseCategoryType.stringValue().as("courseCategoryType")
                        , courseCategory.name.as("courseName")
                )
        ).from(courseCategory).fetch();
    }

    @Transactional
    public void saveCourse(CourseVO.RequestSaveVO requestSaveVO) throws IOException {
        Long courseCategoryId = Long.valueOf(requestSaveVO.getCategory());
        long courseFee = Long.parseLong(requestSaveVO.getCourseFee().replace(",", ""));

        Course saveCourse = Course.builder()
                .courseCategory(jpaQueryFactory.selectFrom(courseCategory).where(courseCategory.courseCategoryId.eq(courseCategoryId)).fetchOne())
                .applyStartDate(LocalDate.parse(requestSaveVO.getApplyStartDate()))
                .applyEndDate(LocalDate.parse(requestSaveVO.getApplyEndDate()))
                .courseStartDate(LocalDate.parse(requestSaveVO.getStartDate()))
                .courseEndDate(LocalDate.parse(requestSaveVO.getEndDate()))
                .courseStartTime(LocalTime.parse(requestSaveVO.getStartTime()))
                .courseEndTime(LocalTime.parse(requestSaveVO.getEndTime()))
                .createdAt(LocalDateTime.now())
                .currentEnrollment(0)
                .description(requestSaveVO.getCourseDescription())
                .isPublished(requestSaveVO.getIsPublished().equals("Y"))
                .location(requestSaveVO.getCourseLocation())
                .maxCapacity(requestSaveVO.getRecruitmentCount())
                .price((int) courseFee)
                .registrationPopupMessage(requestSaveVO.getPopupNotice())
                .title(requestSaveVO.getCourseName())
                .updatedAt(LocalDateTime.now())
                .writtenApplicationCount(requestSaveVO.getWrittenApplicationCount())
                .build();

        entityManager.persist(saveCourse);

        if (!requestSaveVO.getCourseThumbnail().isEmpty()) {
            UploadFiles uploadFiles = UploadFiles.builder()
                    .fileData(requestSaveVO.getCourseThumbnail().getBytes())
                    .fileName(requestSaveVO.getCourseThumbnail().getName())
                    .postId(saveCourse.getCourseId())
                    .uploadFileType(UploadFileType.COURSE_THUMBNAIL)
                    .build();

            entityManager.persist(uploadFiles);
        }

    }

    public List<CourseDto> getCourseList(Integer pageSize, Integer page, CourseCategoryType courseCategoryType, CourseCategory courseCategoryName) {
        int offset = (page - 1) * pageSize;
        return jpaQueryFactory.select(Projections.bean(CourseDto.class,
                        course.courseId
                        , course.title
                        , course.courseStartDate
                        , course.courseEndDate
                        , course.applyStartDate
                        , course.applyEndDate
                        , course.courseStartTime
                        , course.courseEndTime
                        , course.maxCapacity
                        , course.writtenApplicationCount
                        , course.location
                        , course.price
                        , course.courseCategory.courseCategoryId
                        , courseCategory.courseCategoryType
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where(courseCategory.courseCategoryType.eq(courseCategoryType.getName()).and(courseCategory.name.eq(courseCategoryName.getCourseCategoryTypeText())))
                .orderBy(course.applyStartDate.desc())
                .limit(pageSize)
                .offset(offset)
                .fetch();
    }

    public CourseCategoryDto getCourseCategoryById(Long courseCategoryId) {
        return jpaQueryFactory.select(
                        Projections.bean(CourseCategoryDto.class,
                                courseCategory.courseCategoryId.as("id")
                                , courseCategory.courseCategoryType.stringValue().as("courseCategoryType")
                                , courseCategory.name.as("courseName")
                        )
                ).from(courseCategory)
                .where(courseCategory.courseCategoryId.eq(courseCategoryId))
                .fetchOne();
    }


}
