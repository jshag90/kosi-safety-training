package com.kosi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lecture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, length = 255)
    private String title;

    @OneToOne
    @JoinColumn(name = "video_id", referencedColumnName = "idx", nullable = false)
    private Video video; // OneToOne 관계로 Video 엔티티와 연결

    @Column
    private Integer duration; // 강의 길이(분)

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
