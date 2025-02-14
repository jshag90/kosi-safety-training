package com.kosi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto extends DataTablesResponseDto {
    String title;
    String content;
    int views;
    String createdAt;
    String updatedAt;
    boolean isPinned;
    String author;
}
