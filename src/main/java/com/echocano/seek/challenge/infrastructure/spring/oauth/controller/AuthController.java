package com.echocano.seek.challenge.infrastructure.spring.oauth.controller;

import com.echocano.seek.challenge.infrastructure.spring.oauth.dtos.TokenDto;
import com.echocano.seek.challenge.infrastructure.spring.oauth.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 * <p>
 * AuthController class from <a href="https://github.com/danvega/jwt/blob/master/src/main/java/dev/danvega/jwt/Application.java">this link</a>
 * <p>
 * @author danvega
 * @author echocano
 * @since 10/2/24
 */

@Log4j2
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public TokenDto token(Authentication authentication) {
        log.debug("Token requested for user: '{}'", authentication.getName());
        TokenDto tokenDto = tokenService.generateToken(authentication);
        log.debug("Token granted: {}", tokenDto.getToken());
        return tokenDto;
    }
}
