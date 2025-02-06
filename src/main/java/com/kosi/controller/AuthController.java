package com.kosi.controller;

import com.kosi.ResultVO;
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

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

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

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        ResultVO<TokenDto> resultVO = ResultVO.<TokenDto>builder().returnCode(ErrorCode.SUCCESS.getErrorCode())
                                            .msg(ErrorCode.SUCCESS.getErrorMsg())
                                            .data(new TokenDto(accessToken, refreshToken))
                                            .build();
        return new ResponseEntity<>(resultVO, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResultVO<TokenDto>> reissue(
            @RequestBody @Valid RefreshTokenDto requestTokenDto) throws ReusedRefreshTokenException {
        if (!tokenProvider.validateToken(requestTokenDto.getRefreshToken())) {
            log.info("유효하지 않은 RefreshToken 입니다");
        }
        //이미 사용해서 blacklist에 있는 지 검사
        if(redisUtil.hasKey(requestTokenDto.getRefreshToken())){
            log.info("이미 사용된 refresh token, 재로그인 해야함.");
            throw new ReusedRefreshTokenException("이미 로그아웃 된 사용자 Refresh 토큰");
        }

        Authentication authentication = tokenProvider.getAuthentication(requestTokenDto.getRefreshToken());
        String accessToken = tokenProvider.createJwtToken(authentication, "access");
        String refreshToken = tokenProvider.createJwtToken(authentication, "refresh"); //refresh 토큰도 재발급

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        log.info("refresh 재사용 방지 redis 저장 : {}", requestTokenDto.getRefreshToken());
        redisUtil.set(requestTokenDto.getRefreshToken(), authentication.getPrincipal().toString(), refreshTokenValidityTime, TimeUnit.SECONDS);

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
