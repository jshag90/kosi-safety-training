package com.kosi.service;

import com.kosi.util.FilesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class VideoService {

    @Value("${koosi.training.video}")
    private String uploadTrainingVideoPath;

    @PostConstruct
    public void init(){
        uploadTrainingVideoPath = FilesUtil.getPathByOS(uploadTrainingVideoPath);
    }

    public void uploadVideo(MultipartFile file) throws IOException {

        File uploadPath = new File(uploadTrainingVideoPath);
        if (!uploadPath.exists()) uploadPath.mkdirs(); // 디렉토리 생성

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // 파일명 충돌 방지
        File videoFile = new File(uploadTrainingVideoPath+"/" + fileName);
        file.transferTo(videoFile); // 파일 저장

    }
}
