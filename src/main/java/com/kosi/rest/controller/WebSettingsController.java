package com.kosi.rest.controller;

import com.kosi.dto.WebImgDto;
import com.kosi.entity.WebImg;
import com.kosi.service.WebSettingsService;
import com.kosi.util.ErrorCode;
import com.kosi.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/web-settings")
@RequiredArgsConstructor
public class WebSettingsController {

    private final WebSettingsService webSettingsService;

    @PutMapping("/main-slide-images")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResultVO<Void>> uploadMainSlideImages(
            @RequestPart("file") MultipartFile uploadMainSlideImages) throws IOException {

        webSettingsService.uploadMainSlideImage(uploadMainSlideImages);

        ResultVO<Void> resultVO = ResultVO.<Void>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

    @GetMapping("/main-slide-images")
    public ResponseEntity<ResultVO<List<WebImgDto>>> getMainSlideImages(){

        List<WebImgDto> mainSlideImages = webSettingsService.getMainSlideImages();

        ResultVO<List<WebImgDto>> resultVO = ResultVO.<List<WebImgDto>>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(mainSlideImages)
                .build();

        return new ResponseEntity<>(resultVO, HttpStatus.OK);
    }

}
