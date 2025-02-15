package com.kosi.dao;

import com.kosi.dto.NoticeDto;
import com.kosi.entity.NoticeBoard;
import com.kosi.entity.User;
import com.kosi.util.SecurityUtil;
import com.kosi.util.UploadFileType;
import com.kosi.util.query.BoardFileQueryUtil;
import com.kosi.util.query.BoardQueryUtil;
import com.kosi.vo.BoardVO;
import com.kosi.vo.DataTablesRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.*;

import static com.kosi.entity.QNoticeBoard.noticeBoard;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;

    public List<NoticeDto> getNoticeList(DataTablesRequest dataTablesRequest) {
        return jpaQueryFactory.select(
                        Projections.bean(NoticeDto.class, noticeBoard.id
                                , noticeBoard.title
                                , noticeBoard.content
                                , noticeBoard.views
                                , Expressions.stringTemplate("DATE_FORMAT({0}, {1})", noticeBoard.createdAt, "%Y-%m-%d %H:%i:%s").as("createdAt")
                                , Expressions.stringTemplate("DATE_FORMAT({0}, {1})", noticeBoard.updatedAt, "%Y-%m-%d %H:%i:%s").as("updatedAt")
                                , noticeBoard.isPinned
                                , noticeBoard.user.nickname.as("author")
                        )
                ).from(noticeBoard)
                .limit(dataTablesRequest.getPgSize())
                .offset(dataTablesRequest.getOffset())
                .orderBy(noticeBoard.createdAt.desc())
                .fetch();
    }

    public Long saveNotice(BoardVO.SaveNoticeVO saveNoticeVO) {

        Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
        if (!currentUsername.isPresent())
            throw new SecurityException("토큰에서 현재 사용자 이름 조회할 수 없음");

        Optional<User> oneByUsername = userDao.findOneByUsername(currentUsername.get());
        if (!oneByUsername.isPresent())
            throw new SecurityException("DB에 해당 username 없음");

        Query saveNoticeQuery = entityManager.createNativeQuery(BoardQueryUtil.insertNotice());
        saveNoticeQuery.setParameter("content", saveNoticeVO.getContent());
        saveNoticeQuery.setParameter("createdAt", LocalDateTime.now().toString());
        saveNoticeQuery.setParameter("title", saveNoticeVO.getTitle());
        saveNoticeQuery.setParameter("updateAt", LocalDateTime.now().toString());
        saveNoticeQuery.setParameter("userId", oneByUsername.get().getUserId());
        saveNoticeQuery.executeUpdate();

        return jpaQueryFactory.selectFrom(noticeBoard)
                .orderBy(noticeBoard.id.desc())
                .limit(1)
                .fetchOne().getId();
    }

    public void saveNoticeUploadFiles(Long postId, List<MultipartFile> files, List<String> fileReNameList) {
        int nameIndex = 0;
        for (MultipartFile file : files) {
            Query saveBoardFileQuery = entityManager.createNativeQuery(BoardFileQueryUtil.insertBoardFile());
            saveBoardFileQuery.setParameter("fileName", file.getOriginalFilename());
            saveBoardFileQuery.setParameter("fileReName", fileReNameList.get(nameIndex++));
            saveBoardFileQuery.setParameter("postIdx", postId);
            saveBoardFileQuery.setParameter("uploadFileType", UploadFileType.NOTICE.name());
            saveBoardFileQuery.executeUpdate();
        }
    }

}
