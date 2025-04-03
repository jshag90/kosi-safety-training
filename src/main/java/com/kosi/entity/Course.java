package com.kosi.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private boolean isPublished; // 강좌 노출 여부 (기본값 true)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)  // Category와 관계 설정
    private CourseCategory courseCategory; // 강좌의 카테고리

    @Column(nullable = false, length = 255)
    private String title;

    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private LocalTime courseStartTime;
    private LocalTime courseEndTime;
    private LocalDate applyStartDate;
    private LocalDate applyEndDate;

    @Column(nullable = false)
    private Integer maxCapacity; // 모집 인원

    @Column(nullable = false)
    private Integer writtenApplicationCount; // 서면 신청 인원

    @Column(nullable = false)
    private Integer currentEnrollment; // 현재 수강 신청을 받은 인원 수

    private String location;//교육 장소

    @Column(precision = 10, scale = 2)
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String registrationPopupMessage; //수강신청 팝업 안내문

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lecture> lectures;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = true)
    private Instructor instructor;

}
