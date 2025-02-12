package com.kosi.entity;

import com.kosi.util.EnrollmentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 어떤 사용자가 어떤 강좌를 들었고 얼마나 들었는지 확인하는 수강등록 엔티티
 */
@Entity
@Table(name = "enrollment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 기존 Student 대신 User 테이블 참조

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(updatable = false)
    private LocalDateTime enrolledAt = LocalDateTime.now();

    @Column(precision = 5, scale = 2)
    private Double progress; // 강의 진행률 (0.00 ~ 100.00)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status; // 수강 상태

}
