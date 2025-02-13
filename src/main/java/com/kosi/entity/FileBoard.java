package com.kosi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FileBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 게시글 고유 식별자

    @Column(nullable = false)
    private String title;  // 게시글 제목

    @Column(columnDefinition = "TEXT")
    private String content;  // 게시글 내용

    @Column(nullable = false)
    private String author;  // 작성자

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // 게시글 작성 시간

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // 게시글 수정 시간

    @Column(name = "view_count", nullable = false)
    private int viewCount;  // 조회수

    @Column(name = "file_name")
    private String fileName;  // 업로드된 파일명

    @Column(name = "file_path")
    private String filePath;  // 파일 저장 경로

    @Column(name = "file_size")
    private Long fileSize;  // 파일 크기 (바이트)

}
