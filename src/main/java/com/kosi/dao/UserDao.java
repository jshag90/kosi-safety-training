package com.kosi.dao;

import static com.kosi.entity.QUser.user;
import static com.kosi.entity.QUserAuthority.userAuthority;
import com.kosi.entity.Authority;
import com.kosi.entity.User;
import com.kosi.entity.UserAuthority;
import com.kosi.util.query.UserQueryUtil;
import com.kosi.vo.MemberVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
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

    public List<UserAuthority> findAuthorityByUsername(String userName) {
        return jpaQueryFactory
                .selectFrom(userAuthority)
                .innerJoin(user).on(userAuthority.user.userId.eq(user.userId))
                .where(user.username.eq(userName))
                .fetch();
    }

    public User saveUser(MemberVO memberVO, Authority saveAuthority) {
        Query saveUserQuery = entityManager.createNativeQuery(UserQueryUtil.insertUser());
        saveUserQuery.setParameter("activated", 1);
        saveUserQuery.setParameter("password", memberVO.getPassword());
        saveUserQuery.setParameter("userName", memberVO.getUsername());
        saveUserQuery.setParameter("agreePersonalInfoCollection", 1);
        saveUserQuery.setParameter("agreePersonalInfoThirdPart", 1);
        saveUserQuery.setParameter("birthday", memberVO.getBirthday());
        saveUserQuery.setParameter("email", memberVO.getEmail());
        saveUserQuery.setParameter("phoneNumber", memberVO.getPhoneNumber());
        saveUserQuery.setParameter("name", memberVO.getName());
        saveUserQuery.setParameter("companyName", memberVO.getCompanyName());
        saveUserQuery.setParameter("companyNumber", memberVO.getCompanyNumber());
        saveUserQuery.executeUpdate();

        User insertedUser = jpaQueryFactory.selectFrom(user).orderBy(user.userId.desc()).limit(1).fetchOne();
        Long userId = insertedUser.getUserId();

        Query saveUserAuthorityQuery = entityManager.createNativeQuery(UserQueryUtil.insertAuthorityQuery());
        saveUserAuthorityQuery.setParameter("userId", userId);
        saveUserAuthorityQuery.setParameter("authorityName", saveAuthority.getAuthorityName());
        saveUserAuthorityQuery.executeUpdate();
        return insertedUser;
    }


}
