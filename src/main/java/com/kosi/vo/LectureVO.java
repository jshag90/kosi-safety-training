package com.kosi.vo;

import lombok.*;

public class LectureVO {


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestSave{
        Long courseId;
        String title;
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
