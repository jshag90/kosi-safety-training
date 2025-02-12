package com.kosi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "instructor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;  // 강사는 User 테이블을 참조

    @Column(columnDefinition = "TEXT")
    private String bio;
}
