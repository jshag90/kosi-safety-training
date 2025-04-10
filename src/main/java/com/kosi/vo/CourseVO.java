package com.kosi.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class CourseVO {


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class RequestSaveVO {
        private String isPublished;
        private String category;
        private String courseName;
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        private String applyStartDate;
        private String applyEndDate;
        private int recruitmentCount;
        private int writtenApplicationCount;
        private String courseLocation;
        private String courseFee;
        private String courseDescription;
        private String popupNotice;
        private MultipartFile courseThumbnail;
        private MultipartFile courseNotice;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class RequestUpdateVO {
        private Long courseId;
        private String isPublished;
        private String category;
        private String courseName;
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        private String applyStartDate;
        private String applyEndDate;
        private Integer recruitmentCount;
        private Integer writtenApplicationCount;
        private String courseLocation;
        private String courseFee;
        private String courseDescription;
        private String popupNotice;
        private MultipartFile courseThumbnail;
        private MultipartFile courseNotice;
    }



}
