package com.kosi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqDto extends DataTablesResponseDto {
    Long id;
    String faqTypeText;
    String question;
}



