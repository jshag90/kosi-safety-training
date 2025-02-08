package com.kosi.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoVO {

    private String videoName;
    private String durationTime;
}
