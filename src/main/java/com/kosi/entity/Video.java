package com.kosi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length = 255)
    private String videoName;

    @Column(length = 50)
    private String durationTime;

    @Column(nullable = false, length = 500)
    private String filePath;

}
