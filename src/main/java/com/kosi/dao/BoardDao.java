package com.kosi.dao;

import com.kosi.dto.FaqAnswerDto;
import com.kosi.dto.FaqDto;
import com.kosi.dto.NoticeDto;
import com.kosi.entity.UploadFiles;
import com.kosi.entity.User;
import com.kosi.util.FilesUtil;
import com.kosi.util.SecurityUtil;
import com.kosi.util.UploadFileType;
import com.kosi.util.query.BoardFileQueryUtil;
import com.kosi.util.query.BoardQueryUtil;
import com.kosi.vo.BoardVO;
import com.kosi.vo.DataTablesRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.kosi.util.FaqTypeText;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

import static com.kosi.entity.QNoticeBoard.noticeBoard;
import static com.kosi.entity.QFaqBoard.faqBoard;
import static com.kosi.entity.QFaqType.faqType;
import static com.kosi.entity.QUploadFiles.uploadFiles;
import static com.kosi.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Value("${koosi.board.notice.file}")
    private String updateNoticeFilePath;

    @PostConstruct
    public void init() {
        updateNoticeFilePath = FilesUtil.getPathByOS(updateNoticeFilePath);
    }

    public List<NoticeDto> getNoticeList(DataTablesRequest dataTablesRequest) {
        BooleanExpression isExistsUploadFile = JPAExpressions.selectOne()
                                                            .from(uploadFiles)
                                                            .where(uploadFiles.uploadFileType.eq(UploadFileType.NOTICE)
                                                                    .and(uploadFiles.postId.eq(noticeBoard.id)))
                                                            .exists();

        return jpaQueryFactory.select(
                        Projections.bean(NoticeDto.class,
                                noticeBoard.id,
                                noticeBoard.title,
                                noticeBoard.content,
                                noticeBoard.views,
                                Expressions.stringTemplate("DATE_FORMAT({0}, {1})", noticeBoard.createdAt, "%Y-%m-%d %H:%i:%s").as("createdAt"),
                                Expressions.stringTemplate("DATE_FORMAT({0}, {1})", noticeBoard.updatedAt, "%Y-%m-%d %H:%i:%s").as("updatedAt"),
                                noticeBoard.isPinned,
                                user.nickname.as("author"),
                                isExistsUploadFile.as("hasUploadFile")
                        )
                )
                .from(noticeBoard)
                .innerJoin(user).on(noticeBoard.user.userId.eq(user.userId))
                .limit(dataTablesRequest.getPgSize())
                .where(getNoticeWhereQuery(dataTablesRequest))
                .offset(dataTablesRequest.getOffset())
                .orderBy(noticeBoard.createdAt.desc())
                .fetch();
    }

    private static BooleanExpression getNoticeWhereQuery(DataTablesRequest dataTablesRequest) {
        BooleanExpression whereQuery = null;
        switch (dataTablesRequest.getSearchField()) {
            case "title":
                whereQuery = noticeBoard.title.contains(dataTablesRequest.getSearchWord());
                break;
            case "author":
                whereQuery = user.nickname.contains(dataTablesRequest.getSearchWord());
                break;
            default:
                break;
        }
        return whereQuery;
    }

    public Long getTotalByNoticeList(DataTablesRequest dataTablesRequest){
        return jpaQueryFactory.selectFrom(noticeBoard).where(getNoticeWhereQuery(dataTablesRequest)).fetchCount();
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

    public void saveNoticeUploadFiles(Long postId, Optional<List<MultipartFile>> files, List<String> fileReNameList) {
        int nameIndex = 0;
        if(files.isPresent()) {
            for (MultipartFile file : files.get()) {
                Query saveBoardFileQuery = entityManager.createNativeQuery(BoardFileQueryUtil.insertBoardFile());
                saveBoardFileQuery.setParameter("fileName", file.getOriginalFilename());
                saveBoardFileQuery.setParameter("fileReName", fileReNameList.get(nameIndex++));
                saveBoardFileQuery.setParameter("postId", postId);
                saveBoardFileQuery.setParameter("uploadFileType", UploadFileType.NOTICE.name());
                saveBoardFileQuery.executeUpdate();
            }
        }
    }

    public void updateNotice(BoardVO.UpdateNoticeVO updateNoticeVO) {

        jpaQueryFactory.update(noticeBoard)
                .set(noticeBoard.title, updateNoticeVO.getTitle())
                .set(noticeBoard.content, updateNoticeVO.getContent())
                .where(noticeBoard.id.eq(updateNoticeVO.getId()))
                .execute();

        //수정 시 첨부파일 모두 지울 경우
        BooleanExpression noticeFileAllWhereQuery = uploadFiles.postId.eq(updateNoticeVO.getId()).and(uploadFiles.uploadFileType.eq(UploadFileType.NOTICE));
        if (updateNoticeVO.getUploadFileIds().size() < 1) {
            List<UploadFiles> uploadFilesList = jpaQueryFactory.select(Projections.bean(UploadFiles.class, uploadFiles.fileReName))
                                                                .from(uploadFiles)
                                                                .where(noticeFileAllWhereQuery)
                                                                .fetch();
            deleteFileByUploadFileList(uploadFilesList);

            jpaQueryFactory.delete(uploadFiles).where(noticeFileAllWhereQuery).execute();
            return;
        }

        //수정 시 첨부파일을 일부만 지웠을 경우
        BooleanExpression noticeFileSomeWhereQuery = noticeFileAllWhereQuery.and(uploadFiles.id.notIn(updateNoticeVO.getUploadFileIds()));
        if (updateNoticeVO.getUploadFileIds().size() != jpaQueryFactory.selectFrom(uploadFiles).where(noticeFileAllWhereQuery).fetch().size()) {
            List<UploadFiles> uploadFilesList = jpaQueryFactory.select(Projections.bean(UploadFiles.class, uploadFiles.fileReName))
                                                                .from(uploadFiles)
                                                                .where(noticeFileSomeWhereQuery)
                                                                .fetch();
            deleteFileByUploadFileList(uploadFilesList);

            jpaQueryFactory.delete(uploadFiles).where(noticeFileSomeWhereQuery).execute();
        }

    }

    /**
     * 공지사항 첨부 실제 파일 삭제
     * @param uploadFilesList
     */
    private void deleteFileByUploadFileList(List<UploadFiles> uploadFilesList) {
        for (UploadFiles uploadFile : uploadFilesList) {
            File deleteFilePath = new File(updateNoticeFilePath + "/" + uploadFile.getFileReName());
            if (deleteFilePath.exists()) {
                deleteFilePath.delete();
            }
        }
    }

    public BoardVO.FindOneNoticeVO findOneNotice(Long id) {
        BoardVO.FindOneNoticeVO findOneNoticeVO = jpaQueryFactory.select(Projections.bean(BoardVO.FindOneNoticeVO.class
                        , noticeBoard.title
                        , noticeBoard.content
                )).from(noticeBoard)
                .where(noticeBoard.id.eq(id)).fetchOne();

        findOneNoticeVO.setFileNames(jpaQueryFactory.select(uploadFiles.fileName).from(uploadFiles)
                                                    .where(uploadFiles.postId.eq(id)).fetch());

        return findOneNoticeVO;
    }

    public List<FaqDto> getFaqList(DataTablesRequest dataTablesRequest) {

        BooleanExpression whereQuery = null;
        if (!dataTablesRequest.getSearchField().equals("ALL")) {
            whereQuery = faqBoard.faqType.faqTypeText.eq(dataTablesRequest.getSearchWord());
        }

        if(!dataTablesRequest.getSearchWord().isEmpty()){
            if( whereQuery != null) {
                whereQuery.and(faqBoard.answer.contains(dataTablesRequest.getSearchWord()))
                        .or(faqBoard.question.contains(dataTablesRequest.getSearchWord()));
            } else {
                whereQuery = faqBoard.answer.contains(dataTablesRequest.getSearchWord())
                        .or(faqBoard.question.contains(dataTablesRequest.getSearchWord()));
            }
        }

        return jpaQueryFactory.select(
                        Projections.bean(FaqDto.class,
                                faqBoard.id,
                                faqBoard.faqType.faqTypeText,
                                faqBoard.question
                        )
                )
                .from(faqBoard)
                .innerJoin(faqType).on(faqBoard.faqType.id.eq(faqType.id))
                .where(whereQuery)
                .limit(dataTablesRequest.getPgSize())
                .offset(dataTablesRequest.getOffset())
                .orderBy(faqBoard.createdAt.desc())
                .fetch();
    }


    public Long getTotalByFaqList(DataTablesRequest dataTablesRequest){
        BooleanExpression whereQuery = null;
        if (!dataTablesRequest.getSearchField().equals("ALL")) {
            whereQuery = faqBoard.faqType.faqTypeText.eq(dataTablesRequest.getSearchWord());
        }

        if(!dataTablesRequest.getSearchWord().isEmpty()){
            if( whereQuery != null) {
                whereQuery.and(faqBoard.answer.contains(dataTablesRequest.getSearchWord()))
                        .or(faqBoard.question.contains(dataTablesRequest.getSearchWord()));
            } else {
                whereQuery = faqBoard.answer.contains(dataTablesRequest.getSearchWord())
                        .or(faqBoard.question.contains(dataTablesRequest.getSearchWord()));
            }
        }
        return jpaQueryFactory.selectFrom(faqBoard).where(whereQuery).fetchCount();
    }

    @Transactional
    public void insertFaqType() {
        if (jpaQueryFactory.selectFrom(faqType).fetchCount() < 1) {
            Query saveFaqTypeQuery = entityManager.createNativeQuery(BoardQueryUtil.insertFaqType());
            for (FaqTypeText type : FaqTypeText.values()) {
                saveFaqTypeQuery.setParameter("faqTypeText", type.getFaqTypeText());
                saveFaqTypeQuery.executeUpdate();
            }
        }
    }

    public FaqAnswerDto getFaqAnswerById(Long id) {
        return jpaQueryFactory.select(Projections.bean(
                        FaqAnswerDto.class, faqBoard.answer))
                .from(faqBoard)
                .where(faqBoard.id.eq(id))
                .fetchOne();
    }

}
