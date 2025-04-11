package com.kosi.dao;

import com.kosi.dto.CourseCategoryDto;
import com.kosi.dto.CourseDto;
import com.kosi.dto.LectureDto;
import com.kosi.entity.Course;
import com.kosi.entity.Lecture;
import com.kosi.entity.UploadFiles;
import com.kosi.util.*;
import com.kosi.vo.CourseVO;
import com.kosi.vo.LectureVO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
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

    private static final StringTemplate toBase64StringTemplate = Expressions.stringTemplate("function('TO_BASE64', {0})", uploadFiles.fileData);
    private final JPAQueryFactory jpaQueryFactory;
    @PersistenceContext
    private EntityManager entityManager;

    private static JPQLQuery<String> getCourseThumbnailSubQuery() {
        return JPAExpressions.select(toBase64StringTemplate)
                .from(uploadFiles)
                .where(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL),
                        uploadFiles.postId.eq(course.courseId))
                .limit(1);
    }

    private static JPQLQuery<String> getCourseThumbnailFileNameSubQuery() {
        return JPAExpressions.select(uploadFiles.fileName)
                .from(uploadFiles)
                .where(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL),
                        uploadFiles.postId.eq(course.courseId))
                .limit(1);
    }

    private static JPQLQuery<String> getCourseNoticeFileNameSubQuery() {
        return JPAExpressions.select(uploadFiles.fileName)
                .from(uploadFiles)
                .where(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_NOTICE_FILE),
                        uploadFiles.postId.eq(course.courseId))
                .limit(1);
    }

    private static JPQLQuery<Integer> getEnrollmentCountSubQuery() {
        return JPAExpressions
                .select(Expressions.numberTemplate(Integer.class, "count(*)"))
                .from(enrollment)
                .where(
                        enrollment.course.courseId.eq(course.courseId),
                        enrollment.status.eq(EnrollmentStatus.APPLY)
                );
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
                    .fileName(requestSaveVO.getCourseThumbnail().getOriginalFilename())
                    .postId(saveCourse.getCourseId())
                    .uploadFileType(UploadFileType.COURSE_THUMBNAIL)
                    .build();

            entityManager.persist(uploadFiles);
        }

        if (!requestSaveVO.getCourseNotice().isEmpty()) {
            UploadFiles uploadFiles = UploadFiles.builder()
                    .fileData(requestSaveVO.getCourseNotice().getBytes())
                    .fileName(requestSaveVO.getCourseNotice().getOriginalFilename())
                    .postId(saveCourse.getCourseId())
                    .uploadFileType(UploadFileType.COURSE_NOTICE_FILE)
                    .build();

            entityManager.persist(uploadFiles);
        }

    }

    public List<CourseDto> getCourseList(Integer pageSize, Integer page, CourseCategoryType courseCategoryType, CourseCategory courseCategoryName) {

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
                        , ExpressionUtils.as(getCourseThumbnailSubQuery(), "courseThumbnailBase64")
                        , ExpressionUtils.as(getEnrollmentCountSubQuery(), "currentEnrollment")
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where(courseCategory.courseCategoryType.eq(courseCategoryType.getName())
                        .and(courseCategory.name.eq(courseCategoryName.getCourseCategoryTypeText()))
                        .and(course.isPublished.eq(true))
                )
                .orderBy(course.applyStartDate.desc())
                .limit(pageSize)
                .offset(PagingUtil.getOffset(page, pageSize))
                .fetch();
    }

    public CourseDto getCourseByCourseId(Long courseId) {
        return jpaQueryFactory.select(Projections.bean(CourseDto.class,
                        course.courseId
                        , course.isPublished
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
                        , ExpressionUtils.as(getCourseThumbnailSubQuery(), "courseThumbnailBase64")
                        , ExpressionUtils.as(getEnrollmentCountSubQuery(), "currentEnrollment")
                        , ExpressionUtils.as(getCourseThumbnailFileNameSubQuery(), "courseThumbnailFileName")
                        , ExpressionUtils.as(getCourseNoticeFileNameSubQuery(), "courseNoticeFileName")
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where(course.courseId.eq(courseId)).fetchOne();
    }

    public QueryResults<CourseDto> getCourses(Integer pageSize, Integer page) {
        Expression<String> formattedCourseDate = ExpressionUtils.as(
                Expressions.stringTemplate(
                        "CONCAT({0}, ' ~ ', {1})",
                        course.courseStartDate,
                        course.courseEndDate
                ),
                "formattedCourseDate"
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
                        , course.isPublished
                        , courseCategory.courseCategoryId
                        , courseCategory.courseCategoryType
                        , ExpressionUtils.as(getCourseThumbnailSubQuery(), "courseThumbnailBase64")
                        , ExpressionUtils.as(getEnrollmentCountSubQuery(), "currentEnrollment")
                        , formattedCourseDate
                )).from(course)
                .innerJoin(courseCategory).on(course.courseCategory.courseCategoryId.eq(courseCategory.courseCategoryId))
                .where()
                .orderBy(course.createdAt.desc())
                .limit(pageSize)
                .offset(PagingUtil.getOffset(page, pageSize))
                .fetchResults();
    }

    public void deleteCourseById(Long courseId) {

        List<Long> deletedLectureIds = getDeletedLectureIds(courseId);

        jpaQueryFactory.delete(video).where(video.lecture.lectureId.in(deletedLectureIds)).execute();
        jpaQueryFactory.delete(lecture).where(lecture.course.courseId.eq(courseId)).execute();

        jpaQueryFactory.delete(enrollment).where(enrollment.course.courseId.eq(courseId)).execute();
        jpaQueryFactory.delete(course).where(course.courseId.eq(courseId)).execute();

        jpaQueryFactory.delete(uploadFiles).where(uploadFiles.postId.eq(courseId).and(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_NOTICE_FILE))).execute();
        jpaQueryFactory.delete(uploadFiles).where(uploadFiles.postId.eq(courseId).and(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL))).execute();

    }

    private List<Long> getDeletedLectureIds(Long courseId) {
        return jpaQueryFactory.select(lecture.lectureId).from(lecture).where(lecture.course.courseId.eq(courseId)).fetch();
    }

    public void updateCourse(CourseVO.RequestUpdateVO requestUpdateVO) throws IOException {
        String courseFee = requestUpdateVO.getCourseFee().replace(",", "");
        jpaQueryFactory.update(course)
                .set(course.isPublished, requestUpdateVO.getIsPublished().equals("true"))
                .set(course.title, requestUpdateVO.getCourseName())
                .set(course.description, requestUpdateVO.getCourseDescription())
                .set(course.courseStartDate, LocalDate.parse(requestUpdateVO.getStartDate()))
                .set(course.courseEndDate, LocalDate.parse(requestUpdateVO.getEndDate()))
                .set(course.courseStartTime, LocalTime.parse(requestUpdateVO.getStartTime()))
                .set(course.courseEndTime, LocalTime.parse(requestUpdateVO.getEndTime()))
                .set(course.applyStartDate, LocalDate.parse(requestUpdateVO.getApplyStartDate()))
                .set(course.applyEndDate, LocalDate.parse(requestUpdateVO.getApplyEndDate()))
                .set(course.maxCapacity, requestUpdateVO.getRecruitmentCount())
                .set(course.writtenApplicationCount, requestUpdateVO.getWrittenApplicationCount())
                .set(course.location, requestUpdateVO.getCourseLocation())
                .set(course.price, Integer.parseInt(courseFee))
                .set(course.description, requestUpdateVO.getCourseDescription())
                .set(course.registrationPopupMessage, requestUpdateVO.getPopupNotice())
                .where(course.courseId.eq(requestUpdateVO.getCourseId()))
                .execute();

        if (!requestUpdateVO.getCourseThumbnail().isEmpty()) {
            jpaQueryFactory.update(uploadFiles)
                    .set(uploadFiles.fileData, requestUpdateVO.getCourseThumbnail().getBytes())
                    .set(uploadFiles.fileName, requestUpdateVO.getCourseThumbnail().getOriginalFilename())
                    .where(uploadFiles.postId.eq(requestUpdateVO.getCourseId())
                            .and(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_THUMBNAIL)))
                    .execute();
        }

        if(!requestUpdateVO.getCourseNotice().isEmpty()){
            jpaQueryFactory.update(uploadFiles)
                    .set(uploadFiles.fileData, requestUpdateVO.getCourseNotice().getBytes())
                    .set(uploadFiles.fileName, requestUpdateVO.getCourseNotice().getOriginalFilename())
                    .where(uploadFiles.postId.eq(requestUpdateVO.getCourseId())
                            .and(uploadFiles.uploadFileType.eq(UploadFileType.COURSE_NOTICE_FILE)))
                    .execute();
        }

    }

    public QueryResults<LectureDto> getLectures(Long courseId, Integer pageSize, Integer page) {
        return jpaQueryFactory.select(Projections.bean(LectureDto.class, lecture.lectureId
                , lecture.title
                , lecture.lectureOrder
                )).from(lecture)
                .where(lecture.course.courseId.eq(courseId))
                .orderBy(lecture.lectureOrder.asc())
                .fetchResults();
    }

    public void updateLectureOrder(Long courseId, List<LectureVO.OrderVO> lectureOrderVOList) {
        for (LectureVO.OrderVO lectureOrderVo : lectureOrderVOList) {
            jpaQueryFactory.update(lecture)
                    .set(lecture.lectureOrder, lectureOrderVo.getReOrderNumber())
                    .where(lecture.course.courseId.eq(courseId).and(lecture.lectureId.eq(lectureOrderVo.getLectureId())))
                    .execute();
        }
    }

    public void saveLecture(LectureVO.RequestSave requestSave) {
        Course findByIdCourse = jpaQueryFactory.selectFrom(course).where(course.courseId.eq(requestSave.getCourseId())).fetchOne();

        LectureDto lectureOrder = jpaQueryFactory.select(Projections.bean(LectureDto.class, lecture.lectureOrder))
                                                .from(lecture)
                                                .orderBy(lecture.lectureOrder.desc())
                                                .limit(1)
                                                .fetchOne();

        Lecture saveLecture = Lecture.builder()
                .title(requestSave.getTitle())
                .course(findByIdCourse)
                .createdAt(LocalDateTime.now())
                .lectureOrder(lectureOrder==null?1:lectureOrder.getLectureOrder()+1)
                .build();

        entityManager.persist(saveLecture);
    }
}
