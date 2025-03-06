package com.kosi.dao;

import com.kosi.util.CourseCategory;
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
            for (CourseCategory category : CourseCategory.values()) {
                saveCourseCategoryQuery.setParameter("name", category.getCourseCategoryTypeText());
                saveCourseCategoryQuery.executeUpdate();
            }
        }
    }
}
