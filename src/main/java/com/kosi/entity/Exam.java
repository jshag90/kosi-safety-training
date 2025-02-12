package com.kosi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 시험 관련 테이블
 */
@Entity
@Table(name = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private LocalDate examDate;

    @Column(nullable = false)
    private Integer maxScore = 100;
}
