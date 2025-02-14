package com.kosi.service;

import com.kosi.dao.BoardDao;
import com.kosi.dto.NoticeDto;
import com.kosi.vo.DataTablesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao boardDao;

    public List<NoticeDto> getNoticeList(DataTablesRequest dataTablesRequest) {
        return boardDao.getNoticeList(dataTablesRequest);
    }

}
