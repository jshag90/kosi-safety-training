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

    @ManyToOne
    private FaqType faqTypeBoard;

    @Column(nullable = false)
    private String question;  // FAQ 질문 내용

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;  // FAQ 답변 내용 (긴 텍스트를 위해 TEXT 타입 사용)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // FAQ 항목 생성 시간

}
