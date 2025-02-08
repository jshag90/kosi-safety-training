package com.kosi.service;

import com.kosi.entity.Video;
import com.kosi.repository.VideoRepository;
import com.kosi.util.FilesUtil;
import com.kosi.util.VideoUtil;
import com.kosi.vo.VideoVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;


import static com.kosi.entity.QVideo.video;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final JPAQueryFactory jpaQueryFactory;
    @Value("${koosi.training.video}")
    private String uploadTrainingVideoPath;

    @PostConstruct
    public void init() {
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

    public List<VideoVO> getVideoList() {
        return jpaQueryFactory.select(Projections.bean(VideoVO.class
                        , video.videoName
                        , video.durationTime))
                .from(video)
                .orderBy(video.idx.desc())
                .fetch();
    }


}
