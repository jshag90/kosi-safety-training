package com.kosi.controller;

import com.kosi.ResultVO;
import com.kosi.dto.TokenDto;
import com.kosi.exception.ReusedRefreshTokenException;
import com.kosi.util.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiAdviceController {

    @ExceptionHandler(ReusedRefreshTokenException.class)
    public ResponseEntity<ResultVO<String>> handleValidationExceptions(ReusedRefreshTokenException ex) {
        ResultVO<String> resultVO = ResultVO.<String>builder()
                .returnCode(ErrorCode.REUSED_REFRESH_TOKEN.getErrorCode())
                .msg(ErrorCode.REUSED_REFRESH_TOKEN.getErrorMsg())
                .data("")
                .build();

        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

}