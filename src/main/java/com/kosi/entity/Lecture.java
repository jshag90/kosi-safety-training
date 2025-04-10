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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, length = 255)
    private String title;

    private Integer lectureOrder;

    @OneToOne
    @JoinColumn(name = "video_id", referencedColumnName = "idx", nullable = true)
    private Video video;

    @Column
    private Integer duration; // 강의 길이(분)

    @Column(updatable = false)
    private LocalDateTime createdAt;
}
