package com.kosi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseCategory {
    SUPERVISOR_TRAINING_MANUFACTURE("관리감독자 교육(제조업)"),
    SUPERVISOR_TRAINING_ETC("관리감독자 교육(기타업)"),
    REGULAR_TRAINING("근로자 정기교육"),
    ONBOARDING_TRAINING("채용 시 교육"),
    CHANGE_WORK_TRAINING("작업내용 변경 시 교육")
    ;

    private String courseCategoryTypeText;
}
