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
    @Column(columnDefinition = "BIGINT COMMENT '게시글 고유 식별자'")
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '게시글 제목'")
    private String title;

    @Column(columnDefinition = "TEXT COMMENT '게시글 내용'")
    private String content;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME COMMENT '게시글 작성 시간'")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME COMMENT '게시글 수정 시간'")
    private LocalDateTime updatedAt;

    @Column(name = "view_count", nullable = false, columnDefinition = "INT COMMENT '조회수'")
    private int viewCount;

    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '업로드된 파일명'")
    private String fileName;

    @Column(name = "file_path", columnDefinition = "VARCHAR(255) COMMENT '파일 저장 경로'")
    private String filePath;

    @Column(name = "file_size", columnDefinition = "BIGINT COMMENT '파일 크기 (바이트)'")
    private Long fileSize;

    /**
     * 작성자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '작성자 ID (User 테이블 참조)'")
    private User user;
}
