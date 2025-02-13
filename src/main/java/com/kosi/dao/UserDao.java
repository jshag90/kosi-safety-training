package com.kosi.dao;

import static com.kosi.entity.QUser.user;
import static com.kosi.entity.QUserAuthority.userAuthority;

import com.kosi.entity.Authority;
import com.kosi.entity.User;
import com.kosi.entity.UserAuthority;
import com.kosi.util.QueryUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findOneByUsername(String userName) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(user)
                        .where(user.username.eq(userName))
                        .fetchOne()
        );
    }

    public List<UserAuthority> findAuthorityByUsername(String userName){
        return jpaQueryFactory
                .selectFrom(userAuthority)
                .innerJoin(user).on(userAuthority.user.userId.eq(user.userId))
                .where(user.username.eq(userName))
                .fetch();
    }



    public User saveUser(User saveUser, Authority saveAuthority) {
        Query saveUserQuery = entityManager.createNativeQuery(QueryUtil.insertUser());
        saveUserQuery.setParameter("username", saveUser.getUsername());
        saveUserQuery.setParameter("password", saveUser.getPassword());
        saveUserQuery.setParameter("nickname", saveUser.getNickname());
        saveUserQuery.setParameter("activated", saveUser.isActivated());
        saveUserQuery.executeUpdate(); // 삽입 작업

        User insertedUser = jpaQueryFactory.selectFrom(user).orderBy(user.userId.desc()).limit(1).fetchOne();

        System.out.println("savedUserId : "+ insertedUser);
        Query saveUserAuthorityQuery = entityManager.createNativeQuery(QueryUtil.insertAuthorityQuery());
        saveUserAuthorityQuery.setParameter("userId", insertedUser.getUserId());
        saveUserAuthorityQuery.setParameter("authorityName", saveAuthority.getAuthorityName());
        saveUserAuthorityQuery.executeUpdate();

        return insertedUser;

    }


}
