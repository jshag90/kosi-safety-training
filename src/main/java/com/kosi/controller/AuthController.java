package com.kosi.controller;

import com.kosi.dto.LoginDto;
import com.kosi.dto.TokenDto;
import com.kosi.jwt.JwtFilter;
import com.kosi.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createJwtToken(authentication, "access");
        String refreshToken = tokenProvider.createJwtToken(authentication, "refresh");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(
            @RequestBody @Valid TokenDto requestTokenDto) {
        if (!tokenProvider.validateToken(requestTokenDto.getRefreshToken())) {
            log.info("유효하지 않은 RefreshToken 입니다");
        }
        Authentication authentication = tokenProvider.getAuthentication(requestTokenDto.getRefreshToken());
        String accessToken = tokenProvider.createJwtToken(authentication, "access");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken, requestTokenDto.getRefreshToken()), httpHeaders, HttpStatus.OK);
    }
}
