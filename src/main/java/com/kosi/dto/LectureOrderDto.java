package com.kosi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LectureOrderDto {

    private Long lectureId;
    private Integer newPosition;
}
