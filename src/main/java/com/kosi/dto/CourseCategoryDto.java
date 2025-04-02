package com.kosi.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryDto {

    private Long id;
    private String courseCategoryType;
    private String courseName;
}
