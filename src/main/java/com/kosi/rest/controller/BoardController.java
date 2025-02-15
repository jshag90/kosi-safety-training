package com.kosi.rest.controller;

import com.kosi.dto.NoticeDto;
import com.kosi.service.BoardService;
import com.kosi.util.ErrorCode;
import com.kosi.vo.BoardVO;
import com.kosi.vo.DataTablesRequest;
import com.kosi.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
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

    @PostMapping(value = "/notice/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResultVO<Void>> saveNotice(@RequestParam("title") String title,
                                                     @RequestParam("content") String content,
                                                     @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) throws IOException {
        log.info(files.toString());

        BoardVO.SaveNoticeVO saveNoticeVO = BoardVO.SaveNoticeVO.builder()
                .title(title)
                .content(content)
                .build();
        boardService.saveNotice(saveNoticeVO, files);

        ResultVO<Void> resultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

}
