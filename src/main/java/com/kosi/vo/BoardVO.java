package com.kosi.vo;

import lombok.*;

public class BoardVO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SaveNoticeVO {
        String title;
        String content;
    }
}
