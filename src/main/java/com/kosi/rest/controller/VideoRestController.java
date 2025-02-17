package com.kosi.rest.controller;

import com.kosi.service.VideoService;
import com.kosi.util.ErrorCode;
import com.kosi.vo.ResultVO;
import com.kosi.vo.VideoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class VideoRestController {

    private final VideoService videoService;

    @PostMapping("/upload/training-video/name={name}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResultVO<Void>> uploadTrainingVideo(
            @PathVariable("name") String videoName,
            @RequestPart("file") MultipartFile file) throws IOException {

        videoService.uploadVideo(videoName, file);

        ResultVO<Void> resultVO = ResultVO.<Void>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

    @GetMapping("/video/list")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ResultVO<List<VideoVO>>> getVideoList() {

        ResultVO<List<VideoVO>> resultVO = ResultVO.<List<VideoVO>>builder()
                .returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(videoService.getVideoList())
                .build();

        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }


}
