package com.kosi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseThumbnailDto {
    byte[] fileData;
    String base64FileData;
}
