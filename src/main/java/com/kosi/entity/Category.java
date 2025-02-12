package com.kosi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 100)
    private String name; // 카테고리 이름 (예: 프로그래밍, 디자인 등)

    @OneToMany(mappedBy = "category")
    private List<Course> courses; // 해당 카테고리에 속한 강좌들

}
