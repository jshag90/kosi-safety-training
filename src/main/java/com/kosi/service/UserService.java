package com.kosi.service;

import com.kosi.dao.UserDao;
import com.kosi.dto.MemberDto;
import com.kosi.entity.Authority;
import com.kosi.entity.User;
import com.kosi.exception.DuplicateMemberException;
import com.kosi.jwt.TokenProvider;
import com.kosi.util.RedisUtil;
import com.kosi.util.SecurityUtil;
import com.kosi.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

   /* private final UserRepository userRepository;*/
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final TokenProvider tokenProvider;
    private final UserDao userDao;
    @Value("${jwt.refresh-token-validity-time}")
    private long refreshTokenValidityTime;

    @Transactional
    public MemberDto signup(MemberVO memberVO) {
        if (userDao.findOneByUsername(memberVO.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
        Authority authority = Authority.builder().authorityName("ROLE_USER").build();

        User savedMember = userDao.saveUser(memberVO, authority);
        return MemberDto.from(savedMember);
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String username) {
        return MemberDto.from(userDao.findOneByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(userDao::findOneByUsername).orElse(null));
    }

    public void logout(String refreshToken){
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        redisUtil.set(refreshToken, authentication.getPrincipal().toString(), refreshTokenValidityTime, TimeUnit.MILLISECONDS);
    }
}
