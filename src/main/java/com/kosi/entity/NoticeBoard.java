package com.kosi.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 * 공지사항 게시판 엔티티
 */
@Entity
@Table(name = "notice_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeBoard {

    /**
     * 공지사항 ID (PK, 자동 증가)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT COMMENT '공지사항 ID (PK, 자동 증가)'")
    private Long id;

    /**
     * 공지사항 제목 (최대 200자, NULL 불가)
     */
    @Column(nullable = false, length = 200, columnDefinition = "VARCHAR(200) COMMENT '공지사항 제목'")
    private String title;

    /**
     * 공지사항 내용 (대용량 데이터 저장 가능, NULL 불가)
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT COMMENT '공지사항 내용'")
    private String content;


    /**
     * 조회수 (기본값 0, NULL 불가)
     */
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT '조회수'")
    private int views;

    /**
     * 공지사항 생성일 (최초 저장 시 자동 설정, 이후 변경 불가)
     */
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME COMMENT '공지사항 생성일'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * 공지사항 수정일 (매번 업데이트 시 자동 변경)
     */
    @Column(nullable = false, columnDefinition = "DATETIME COMMENT '공지사항 수정일'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /**
     * 상단 고정 여부 (기본값 false, TRUE면 공지사항이 상단에 고정됨)
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '상단 고정 여부 (true=고정, false=일반)'")
    private boolean isPinned;

    /**
     * 작성자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
