package com.kosi.controller;

import com.kosi.vo.ResultVO;
import com.kosi.exception.ReusedRefreshTokenException;
import com.kosi.util.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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