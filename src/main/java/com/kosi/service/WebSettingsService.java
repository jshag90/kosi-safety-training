package com.kosi.service;

import com.kosi.dao.WebSettingsDao;
import com.kosi.dto.WebImgDto;
import com.kosi.entity.WebImg;
import com.sun.xml.bind.v2.runtime.output.Encoded;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebSettingsService {

    private final WebSettingsDao webSettingsDao;

    @Transactional
    public void uploadMainSlideImage(MultipartFile uploadMainSlideImage) throws IOException {
        webSettingsDao.uploadMainSlideImage(uploadMainSlideImage);
    }

    public List<WebImgDto> getMainSlideImages() {
        List<WebImg> mainSlideImages = webSettingsDao.getMainSlideImages();
        List<WebImgDto> mainSlideBase64Images = new ArrayList<>();

        for(WebImg webImg: mainSlideImages){
            WebImgDto base64WebImg = WebImgDto.builder()
                    .imageData(Base64.getEncoder().encodeToString(webImg.getImageData()))
                    .build();

            mainSlideBase64Images.add(base64WebImg);
        }


        return mainSlideBase64Images;
    }
}
