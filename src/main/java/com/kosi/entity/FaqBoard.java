package com.kosi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "faq_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FaqBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // FAQ 항목의 고유 식별자

    @Column(nullable = false)
    private String question;  // FAQ 질문 내용

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;  // FAQ 답변 내용 (긴 텍스트를 위해 TEXT 타입 사용)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // FAQ 항목 생성 시간

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // FAQ 항목 최종 수정 시간

    @Column(name = "is_published", nullable = false)
    private boolean isPublished;  // FAQ 항목 공개 여부 (true: 공개, false: 비공개)

}
