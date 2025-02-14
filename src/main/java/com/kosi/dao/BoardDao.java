package com.kosi.dao;

import com.kosi.dto.NoticeDto;
import com.kosi.vo.DataTablesRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static com.kosi.entity.QNoticeBoard.noticeBoard;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final JPAQueryFactory jpaQueryFactory;

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

}
