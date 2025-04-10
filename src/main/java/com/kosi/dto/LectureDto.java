package com.kosi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LectureDto {

    Long courseId;

    Long lectureId;

    String title;

    String section;

    String videoFileName;

    Integer lectureOrder;

}
