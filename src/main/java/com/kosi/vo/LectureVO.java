package com.kosi.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class LectureVO {


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestSave{
        Long courseId;
        String title;
        MultipartFile video;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class OrderVO {
        private Long lectureId;
        private Integer reOrderNumber;
    }

}
