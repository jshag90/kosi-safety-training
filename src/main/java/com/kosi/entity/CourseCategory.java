package com.kosi.entity;

import com.kosi.util.CourseCategoryType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseCategoryId;

    @Column(nullable = false, length = 100)
    private String name; // 카테고리 이름 (예: 프로그래밍, 디자인 등)

    @Column(nullable = false, length = 50)
    private String courseCategoryType;

    @OneToMany(mappedBy = "courseCategory", fetch = FetchType.LAZY)
    private List<Course> courses; // 해당 카테고리에 속한 강좌들

}
