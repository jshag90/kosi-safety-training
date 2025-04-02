package com.kosi.dao;

import com.kosi.entity.WebImg;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.kosi.entity.QWebImg.webImg;

@Repository
@RequiredArgsConstructor
public class WebSettingsDao {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public void uploadMainSlideImage(MultipartFile uploadMainSlideImage) throws IOException {

        WebImg webImg = WebImg.builder()
                .imageName("main_slide_image")
                .imageData(uploadMainSlideImage.getBytes())
                .createdAt(LocalDateTime.now())
                .build();

        entityManager.persist(webImg);

    }

    public List<WebImg> getMainSlideImages() {
        return jpaQueryFactory.selectFrom(webImg)
                .where(webImg.imageName.contains("main_slide_image"))
                .fetch();
    }
}
