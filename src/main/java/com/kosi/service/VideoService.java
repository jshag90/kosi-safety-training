package com.kosi.service;

import com.kosi.entity.Video;
import com.kosi.repository.VideoRepository;
import com.kosi.util.FilesUtil;
import com.kosi.util.VideoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {

    @Value("${koosi.training.video}")
    private String uploadTrainingVideoPath;

    private final VideoRepository videoRepository;

    @PostConstruct
    public void init(){
        uploadTrainingVideoPath = FilesUtil.getPathByOS(uploadTrainingVideoPath);
    }

    public void uploadVideo(String videoName, MultipartFile file) throws IOException {

        File uploadPath = new File(uploadTrainingVideoPath);
        if (!uploadPath.exists()) uploadPath.mkdirs(); // 디렉토리 생성

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // 파일명 충돌 방지
        String filePath = uploadTrainingVideoPath + "/" + fileName;
        File videoFile = new File(filePath);
        file.transferTo(videoFile); // 파일 저장

        Video video = Video.builder().videoName(videoName)
                .durationTime(VideoUtil.getVideoDuration(filePath))
                .filePath(filePath)
                .build();

        videoRepository.save(video);

    }
}
