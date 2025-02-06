package com.kosi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(0, "성공"),
    REUSED_REFRESH_TOKEN(-2, "Refresh Token 재사용");

    private int errorCode;
    private String errorMsg;
}
