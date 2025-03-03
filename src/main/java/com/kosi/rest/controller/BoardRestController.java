package com.kosi.rest.controller;

import com.kosi.dto.FaqAnswerDto;
import com.kosi.dto.FaqDto;
import com.kosi.dto.ListResp;
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
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/notice/list")
    public ResponseEntity<ResultVO<ListResp<NoticeDto>>> getNoticeList(@RequestBody DataTablesRequest dataTablesRequest) {
        log.info(String.valueOf(dataTablesRequest));
        ResultVO<ListResp<NoticeDto>> resultVO = ResultVO.<ListResp<NoticeDto>>builder()
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
                                                     @RequestPart(value = "files", required = false) Optional<List<MultipartFile>> files
    ) throws IOException {
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

    @PostMapping("/notice/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResultVO<Void>> updateNotice(@RequestBody BoardVO.UpdateNoticeVO updateNoticeVO) {

        boardService.updateNotice(updateNoticeVO);

        ResultVO<Void> resultVO = ResultVO.<Void>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/notice/find-one/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResultVO<BoardVO.FindOneNoticeVO>> findOneNotice(@PathVariable("id") Long id) {
        ResultVO<BoardVO.FindOneNoticeVO> resultVO = ResultVO.<BoardVO.FindOneNoticeVO>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(boardService.findOneNotice(id))
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/faq/list")
    public ResponseEntity<ResultVO<ListResp<FaqDto>>> getFaqList(@RequestBody DataTablesRequest dataTablesRequest) {
        log.info(String.valueOf(dataTablesRequest));
        ResultVO<ListResp<FaqDto>> resultVO = ResultVO.<ListResp<FaqDto>>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(boardService.getFaqList(dataTablesRequest))
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/faq/find-one/answer/{id}")
    public ResponseEntity<ResultVO<FaqAnswerDto>> getAnswerById(@PathVariable("id") Long id){
        ResultVO<FaqAnswerDto> resultVO = ResultVO.<FaqAnswerDto>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(boardService.getFaqAnswerById(id))
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }


}
