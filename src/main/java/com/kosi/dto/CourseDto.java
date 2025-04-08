package com.kosi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseDto {

    Long courseId;

    String title;

    String description;

    LocalDate courseStartDate;
    LocalDate courseEndDate;
    LocalDate applyStartDate;
    LocalDate applyEndDate;

    String courseTimeSum;

    LocalTime courseStartTime;

    String formattedCourseStartTime;

    LocalTime courseEndTime;

    String formattedCourseEndTime;

    String courseQuestion;

    Integer maxCapacity;

    Integer currentEnrollment;

    Integer writtenApplicationCount;

    String location;

    Integer price;

    String courseStatus;

    Long courseCategoryId;

    @JsonIgnore
    String courseCategoryType;

    String courseThumbnailBase64;

    String registrationPopupMessage;

    Boolean isPublished;

}
