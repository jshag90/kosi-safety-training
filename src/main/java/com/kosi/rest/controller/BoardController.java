package com.kosi.rest.controller;

import com.kosi.dto.NoticeDto;
import com.kosi.dto.TokenDto;
import com.kosi.service.BoardService;
import com.kosi.util.ErrorCode;
import com.kosi.vo.DataTablesRequest;
import com.kosi.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/notice/list")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResultVO<List<NoticeDto>>> getNoticeList(@RequestBody DataTablesRequest dataTablesRequest) {
        ResultVO<List<NoticeDto>> resultVO = ResultVO.<List<NoticeDto>>builder()
                                                     .returnCode(ErrorCode.SUCCESS.getErrorCode())
                                                     .msg(ErrorCode.SUCCESS.getErrorMsg())
                                                     .data(boardService.getNoticeList(dataTablesRequest))
                                                     .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }


}
