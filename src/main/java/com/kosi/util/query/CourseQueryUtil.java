package com.kosi.util.query;

public class CourseQueryUtil {

    public static String insertCourseCategory() {
        return "INSERT INTO course_category(course_category_type, name) VALUES (:courseCategoryType, :name)";
    }

}
