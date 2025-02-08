package com.kosi.rest.controller;

import com.kosi.service.VideoService;
import com.kosi.util.ErrorCode;
import com.kosi.vo.ResultVO;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class VideoController {

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


}
