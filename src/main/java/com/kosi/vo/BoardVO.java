package com.kosi.vo;

import lombok.*;
import java.util.*;

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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateNoticeVO {
        Long id;
        String title;
        String content;
        List<Long> uploadFileIds;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindOneNoticeVO {
        String title;
        String content;
        List<String> fileNames;
    }
}
