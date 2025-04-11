package com.kosi.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LectureOrderVO {

    private Long lectureId;
    private Integer reOrderNumber;

}
