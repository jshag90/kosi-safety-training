package com.kosi.service;

import com.kosi.dao.UserDao;
import com.kosi.entity.User;
import com.kosi.entity.UserAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userDao.findOneByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return createUser(username, user);
        } else {
            throw new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다.");
        }
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
        if (!user.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<UserAuthority> authorityByUsername = userDao.findAuthorityByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserAuthority authority : authorityByUsername) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
