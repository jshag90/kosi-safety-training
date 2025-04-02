package com.kosi.dao;

import com.kosi.util.CourseCategory;
import com.kosi.util.CourseCategoryType;
import com.kosi.util.FaqTypeText;
import com.kosi.util.query.BoardFileQueryUtil;
import com.kosi.util.query.BoardQueryUtil;
import com.kosi.util.query.CourseQueryUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static com.kosi.entity.QCourseCategory.courseCategory;

@Repository
@RequiredArgsConstructor
public class CourseDao {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void insertCourseCategoryType() {
        if (jpaQueryFactory.selectFrom(courseCategory).fetchCount() < 1) {
            Query saveCourseCategoryQuery = entityManager.createNativeQuery(CourseQueryUtil.insertCourseCategory());

            for(CourseCategoryType courseCategoryType: CourseCategoryType.values()) {

                for (CourseCategory category : CourseCategory.values()) {

                    if (isExceptionGroupEducation(courseCategoryType, category)) {
                        continue;
                    }

                    saveCourseCategoryQuery.setParameter("courseCategoryType", courseCategoryType.getName());
                    saveCourseCategoryQuery.setParameter("name", category.getCourseCategoryTypeText());
                    saveCourseCategoryQuery.executeUpdate();
                }

            }
        }
    }

    private static boolean isExceptionGroupEducation(CourseCategoryType courseCategoryType, CourseCategory category) {
        boolean isGroupEducation = courseCategoryType.equals(CourseCategoryType.GROUP_EDUCATION);

        if (isGroupEducation && category.equals(CourseCategory.CHANGE_WORK_TRAINING)) {
            return true;
        }

        if (isGroupEducation && category.equals(CourseCategory.ONBOARDING_TRAINING)) {
            return true;
        }

        return isGroupEducation && category.equals(CourseCategory.REGULAR_TRAINING);
    }
}
