package com.kosi.entity;

import com.kosi.util.EnrollmentStatus;
import com.kosi.util.UploadFileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "upload_files")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UploadFileType uploadFileType;

    private Long postId;

    private String fileName;

    private String fileReName;

    @Lob
    @Column(nullable = false)
    private byte[] fileData;

}
