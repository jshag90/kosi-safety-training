package com.kosi.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Slf4j
public class SecurityUtil {
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }

    // 쿠키를 Set-Cookie 헤더 형식의 문자열로 변환하는 메서드
    public static String createCookieString(Cookie cookie) {
        return cookie.getName() + "=" + cookie.getValue() + "; Path=" + cookie.getPath() +
                "; Max-Age=" + cookie.getMaxAge() + "; HttpOnly; Secure";
    }
}
