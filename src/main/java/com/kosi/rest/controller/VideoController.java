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
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @PostMapping(value = "/upload/training-video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "업로드할 비디오 파일", required = true,
                    dataType = "file", paramType = "form")
    })
    public ResponseEntity<ResultVO<Void>> uploadTrainingVideo(@RequestPart("file") MultipartFile file) throws IOException {

        videoService.uploadVideo(file);

        ResultVO<Void> resultVO = ResultVO.<Void>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }


}
