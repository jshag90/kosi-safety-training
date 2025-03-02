package com.kosi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FaqTypeText {
    SIGN_UP("회원가입"), EDUCATION_INFO("교육안내"), APPLY_INFO("신청안내"), PAY_REFUND("결제/환불"), LAW_BASIS("법적근거"), EMPLOYMENT_INSURANCE_REFUND("고용보험환급"), INTERNET_EDUCATION("인터넷교육");
    private String faqTypeText;
}
