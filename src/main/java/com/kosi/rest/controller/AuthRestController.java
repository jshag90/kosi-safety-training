package com.kosi.rest.controller;

import com.kosi.vo.ResultVO;
import com.kosi.dto.LoginDto;
import com.kosi.dto.RefreshTokenDto;
import com.kosi.dto.TokenDto;
import com.kosi.exception.ReusedRefreshTokenException;
import com.kosi.jwt.JwtFilter;
import com.kosi.jwt.TokenProvider;
import com.kosi.service.UserService;
import com.kosi.util.ErrorCode;
import com.kosi.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

import static com.kosi.util.SecurityUtil.createCookieString;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final RedisUtil redisUtil;

    @Value("${jwt.refresh-token-validity-time}")
    private long refreshTokenValidityTime;
    @PostMapping("/authenticate")
    public ResponseEntity<ResultVO<TokenDto>> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createJwtToken(authentication, "access");
        String refreshToken = tokenProvider.createJwtToken(authentication, "refresh");

        HttpHeaders httpHeaders = initCookieByToken(accessToken, refreshToken);

        ResultVO<TokenDto> resultVO = ResultVO.<TokenDto>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                                            .msg(ErrorCode.SUCCESS.getErrorMsg())
                                            .data(new TokenDto(accessToken, refreshToken))
                                            .build();
        return new ResponseEntity<>(resultVO, httpHeaders, HttpStatus.OK);
    }

    private static HttpHeaders initCookieByToken(String accessToken, String refreshToken) {
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true); // JavaScript에서 접근 불가
        accessTokenCookie.setSecure(true); // HTTPS에서만 쿠키가 전송됨
        accessTokenCookie.setPath("/"); // 전체 경로에 대해 유효
        accessTokenCookie.setMaxAge(60 * 60); // 1시간

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60 * 24); // 1일

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", createCookieString(accessTokenCookie));
        httpHeaders.add("Set-Cookie", createCookieString(refreshTokenCookie));
        return httpHeaders;
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResultVO<TokenDto>> reissue(
            @RequestBody @Valid RefreshTokenDto requestTokenDto) throws ReusedRefreshTokenException {
        if (!tokenProvider.validateToken(requestTokenDto.getRefreshToken())) {
            log.info("유효하지 않은 RefreshToken 입니다");
        }
        //이미 사용해서 blacklist에 있는 지 검사
        if(redisUtil.hasKey(requestTokenDto.getRefreshToken())){
            log.info(ErrorCode.REUSED_REFRESH_TOKEN.getErrorMsg());
            throw new ReusedRefreshTokenException(ErrorCode.REUSED_REFRESH_TOKEN.getErrorMsg());
        }

        Authentication authentication = tokenProvider.getAuthentication(requestTokenDto.getRefreshToken());
        String accessToken = tokenProvider.createJwtToken(authentication, "access");
        String refreshToken = tokenProvider.createJwtToken(authentication, "refresh"); //refresh 토큰도 재발급

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        log.info("refresh 재사용 방지 redis 저장 : {}", requestTokenDto.getRefreshToken());
        redisUtil.set(requestTokenDto.getRefreshToken(), authentication.getPrincipal().toString(), refreshTokenValidityTime, TimeUnit.MILLISECONDS);

        ResultVO<TokenDto> resultVO = ResultVO.<TokenDto>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .data(new TokenDto(accessToken, refreshToken))
                .build();
        return new ResponseEntity<>(resultVO, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResultVO<Void>> logout(
            @RequestBody @Valid TokenDto requestTokenDto) {
        userService.logout(requestTokenDto.getRefreshToken());

        ResultVO<Void> resultVO = ResultVO.<Void>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                .msg(ErrorCode.SUCCESS.getErrorMsg())
                .build();
        return new ResponseEntity<>(resultVO, new HttpHeaders(), HttpStatus.OK);
    }


}
