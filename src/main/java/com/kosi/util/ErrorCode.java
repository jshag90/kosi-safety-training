package com.kosi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	    REUSED_REFRESH_TOKEN(-2, "Refresh Token 재사용")
	;

	private int errorCode;
	private String errorMsg;
}
