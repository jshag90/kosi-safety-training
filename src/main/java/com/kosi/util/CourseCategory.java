package com.kosi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseCategory {
    GROUP_COURSE("집체교육"),
    MAIL_COURSE("우편교육"),
    INTERNET_COURSE("인터넷교육"),
    CUSTOM_COURSE("맞춤형교육");

    private String courseCategoryTypeText;
}
