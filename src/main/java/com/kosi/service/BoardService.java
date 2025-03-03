package com.kosi.service;

import com.kosi.dao.BoardDao;
import com.kosi.dto.FaqAnswerDto;
import com.kosi.dto.FaqDto;
import com.kosi.dto.ListResp;
import com.kosi.dto.NoticeDto;
import com.kosi.util.FilesUtil;
import com.kosi.vo.BoardVO;
import com.kosi.vo.DataTablesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao boardDao;

    @Value("${koosi.board.notice.file}")
    private String updateNoticeFilePath;

    @PostConstruct
    public void init() {
        updateNoticeFilePath = FilesUtil.getPathByOS(updateNoticeFilePath);
    }

    public ListResp<NoticeDto> getNoticeList(DataTablesRequest dataTablesRequest) {
        List<NoticeDto> noticeList = boardDao.getNoticeList(dataTablesRequest);
        Long totalCount = boardDao.getTotalByNoticeList(dataTablesRequest);

        //행번호 필드 초기화
        int rowNum = totalCount.intValue() - dataTablesRequest.getOffset();
        for(NoticeDto noticeDto : noticeList){
            noticeDto.setRownum(String.valueOf(rowNum--));
        }
        return ListResp.<NoticeDto>builder()
                .list(noticeList)
                .total(totalCount.intValue())
                .currPg(dataTablesRequest.getPg())
                .lastPg((totalCount.intValue()/dataTablesRequest.getPgSize())+1)
                .build();
    }

    @Transactional
    public void saveNotice(BoardVO.SaveNoticeVO saveNoticeVO, Optional<List<MultipartFile>> files) throws IOException {

        Long saveNoticeId = boardDao.saveNotice(saveNoticeVO);

        //첨부파일 저장
        List<String> fileReNameList = new ArrayList<>();
        if(files.isPresent() && files.get().size() > 0) {
            for(MultipartFile file : files.get()) {
                File uploadPath = new File(updateNoticeFilePath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs(); // 디렉토리 생성
                }

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // 파일명 충돌 방지
                fileReNameList.add(fileName);
                file.transferTo(new File(updateNoticeFilePath + "/" + fileName)); // 파일 저장
            }
        }

        boardDao.saveNoticeUploadFiles(saveNoticeId, files, fileReNameList);
    }

    @Transactional
    public void updateNotice(BoardVO.UpdateNoticeVO updateNoticeVO) {
        boardDao.updateNotice(updateNoticeVO);
    }

    public BoardVO.FindOneNoticeVO findOneNotice(Long id){
        return boardDao.findOneNotice(id);
    }

    public ListResp<FaqDto> getFaqList(DataTablesRequest dataTablesRequest) {

        List<FaqDto> faqList = boardDao.getFaqList(dataTablesRequest);
        Long totalCount = boardDao.getTotalByFaqList(dataTablesRequest);

        //행번호 필드 초기화
        int rowNum = totalCount.intValue() - dataTablesRequest.getOffset();
        for(FaqDto faqDto : faqList){
            faqDto.setRownum(String.valueOf(rowNum--));
        }

        return ListResp.<FaqDto>builder()
                .list(faqList)
                .total(totalCount.intValue())
                .currPg(dataTablesRequest.getPg())
                .lastPg((totalCount.intValue()/dataTablesRequest.getPgSize())+1)
                .build();

    }

    public FaqAnswerDto getFaqAnswerById(Long id) {
        return boardDao.getFaqAnswerById(id);
    }

}
