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
public class CourseDto {

    Long courseId;

    String title;

    String description;

    LocalDate courseStartDate;
    LocalDate courseEndDate;
    LocalDate applyStartDate;
    LocalDate applyEndDate;

    String courseTimeSum;

    @JsonIgnore
    LocalTime courseStartTime;

    @JsonIgnore
    LocalTime courseEndTime;

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

}
