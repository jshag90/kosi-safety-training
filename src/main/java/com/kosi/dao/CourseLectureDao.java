package com.kosi.dao;

import com.kosi.dto.CourseCategoryDto;
import com.kosi.dto.CourseDto;
import com.kosi.dto.CourseThumbnailDto;
import com.kosi.entity.Course;
import com.kosi.entity.UploadFiles;
import com.kosi.util.CourseCategory;
import com.kosi.util.CourseCategoryType;
import com.kosi.util.EnrollmentStatus;
import com.kosi.util.UploadFileType;
import com.kosi.vo.CourseVO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.kosi.entity.QCourseCategory.courseCategory;
import static com.kosi.entity.QCourse.course;
import static com.kosi.entity.QEnrollment.enrollment;
import static com.kosi.entity.QUploadFiles.uploadFiles;
import static com.kosi.entity.QLecture.lecture;
import static com.kosi.entity.QVideo.video;

@Repository
@RequiredArgsConstructor
public class CourseLectureDao {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public static JPQLQuery<String> getThumbnailSubquery(Long courseId) {
        return JPAExpressions.select(
                        Expressions.stringTemplate(
                                "function('TO_BASE64', {0})", uploadFiles.fileData
                        )
                )
                .from(uploadFiles)
                .where(
                        uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL),
                        uploadFiles.postId.eq(courseId)
                )
                .limit(1);
    }

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

        if (!requestSaveVO.getCourseNotice().isEmpty()) {
            UploadFiles uploadFiles = UploadFiles.builder()
                    .fileData(requestSaveVO.getCourseNotice().getBytes())
                    .fileName(requestSaveVO.getCourseNotice().getName())
                    .postId(saveCourse.getCourseId())
                    .uploadFileType(UploadFileType.COURSE_NOTICE_FILE)
                    .build();

            entityManager.persist(uploadFiles);
        }

    }

    public List<CourseDto> getCourseList(Integer pageSize, Integer page, CourseCategoryType courseCategoryType, CourseCategory courseCategoryName) {
        int offset = (page - 1) * pageSize;

        JPQLQuery<String> courseThumbnailSubQuery = JPAExpressions.select(
                        Expressions.stringTemplate(
                                "function('TO_BASE64', {0})", uploadFiles.fileData
                        )
                )
                .from(uploadFiles)
                .where(
                        uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL),
                        uploadFiles.postId.eq(course.courseId)
                )
                .limit(1);


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
                        , courseCategory.courseCategoryId
                        , courseCategory.courseCategoryType
                        , ExpressionUtils.as(courseThumbnailSubQuery, "courseThumbnailBase64")
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where(courseCategory.courseCategoryType.eq(courseCategoryType.getName())
                        .and(courseCategory.name.eq(courseCategoryName.getCourseCategoryTypeText()))
                        .and(course.isPublished.eq(true))
                )
                .orderBy(course.applyStartDate.desc())
                .limit(pageSize)
                .offset(offset)
                .fetch();
    }

    public Long getApplyEnrollmentCountByCourseId(Long courseId) {
        return jpaQueryFactory.selectFrom(enrollment)
                .innerJoin(course).on(enrollment.course.courseId.eq(course.courseId))
                .where(course.courseId.eq(courseId)
                        .and(enrollment.status.eq(EnrollmentStatus.APPLY)))
                .fetchCount();
    }

    public CourseDto getCourseByCourseId(Long courseId) {
        return jpaQueryFactory.select(Projections.bean(CourseDto.class,
                        course.courseId
                        , course.title
                        , course.description
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
                        , course.registrationPopupMessage
                        , courseCategory.courseCategoryId
                        , courseCategory.courseCategoryType
                        , ExpressionUtils.as(getThumbnailSubquery(courseId), "courseThumbnailBase64")
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where(course.courseId.eq(courseId)).fetchOne();
    }

    public QueryResults<CourseDto> getCourses(Integer pageSize, Integer page) {
        int offset = (page - 1) * pageSize;

        JPQLQuery<String> courseThumbnailSubQuery = JPAExpressions.select(
                        Expressions.stringTemplate(
                                "function('TO_BASE64', {0})", uploadFiles.fileData
                        )
                )
                .from(uploadFiles)
                .where(
                        uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL),
                        uploadFiles.postId.eq(course.courseId)
                )
                .limit(1);

        JPQLQuery<Integer> applyEnrollmentCountSubQuery = JPAExpressions
                .select(Expressions.numberTemplate(Integer.class, "count(*)"))
                .from(enrollment)
                .where(
                        enrollment.course.courseId.eq(course.courseId),
                        enrollment.status.eq(EnrollmentStatus.APPLY)
                );

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
                        , courseCategory.courseCategoryId
                        , courseCategory.courseCategoryType
                        , ExpressionUtils.as(courseThumbnailSubQuery, "courseThumbnailBase64")
                        , ExpressionUtils.as(applyEnrollmentCountSubQuery, "currentEnrollment") // ✅ 추가된 부분
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where()
                .orderBy(course.createdAt.desc())
                .limit(pageSize)
                .offset(offset)
                .fetchResults();
    }

    public void deleteCourseById(Long courseId) {
        // 1. 삭제될 lecture ID 목록 조회
        List<Long> deletedLectureIds = jpaQueryFactory
                .select(lecture.lectureId)
                .from(lecture)
                .where(lecture.course.courseId.eq(courseId))
                .fetch();

        jpaQueryFactory.delete(video).where(video.lecture.lectureId.in(deletedLectureIds)).execute();
        jpaQueryFactory.delete(lecture).where(lecture.course.courseId.eq(courseId)).execute();

        jpaQueryFactory.delete(course).where(course.courseId.eq(courseId)).execute();
        jpaQueryFactory.delete(uploadFiles).where(uploadFiles.postId.eq(courseId).and(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_NOTICE_FILE)));
        jpaQueryFactory.delete(uploadFiles).where(uploadFiles.postId.eq(courseId).and(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL)));

    }
}
