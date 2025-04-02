package com.kosi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseCategoryType {
    GROUP_EDUCATION("집체교육"), ONLINE_EDUCATION("인터넷 교육");
    private String name;
}
