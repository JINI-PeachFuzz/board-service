package org.koreait.global.interceptors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.UUID;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 사용자 구분 목적의 hash 생성 S */
        String key = "userHash"; // userHash는 그냥 알기쉽게 적은거
        String userKey = "" + Objects.hash(key); // 정수기 때문에 문자열로 변경하기 위해 ""를 추가했음 / 보안을 위해 해시로 만들어줌
        String userHash = null;

        // 우선 쿠키를 들고와야함.
        Cookie[] cookies = request.getCookies();
        if (cookies != null) { // 쿠키가 널이 아닐때 한정.
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(userKey)) { // 위에 유저키와 일치한지 확인 일치하면 브레이크!
                    userHash = cookie.getValue();
                    break;
                }
            }
        }

        userHash = StringUtils.hasText(userHash) ? userHash : UUID.randomUUID().toString(); // 있으면 그냥 쓰고 없으면 생성할거임
        response.setHeader("Set-Cookie", String.format("%s=%s; Path=/; HttpOnly; SameSite=None; Secure", userKey, userHash));
        // 실제 리액트에서 쿠키설정하는 이부분을 구현할건데 다른 플랫폼이라서 SameSite=None을 해줘야함 / 실제 기본기능에서는 없어서 직접 헤더를 셋쿠키로 작성해야함

        /* 사용자 구분 목적의 hash 생성 E */
        return true;

        /*쿠키를 설정하는건 리액트에서 할거라 다른플랫폼이기 때문에 직접 헤더에 쿠키를 설정해줬음 Path=/; 는 전체경로/, Secure는 Https일때만 쿠키공유가 가능하기때문에 추가했음
        쿠키가 아에 없는경우도 있기 때문에 null이 아닐때 조건을 추가함 / HttpOnly : 서버쪽에서만 조회가 가능하게 설정
        -> Set-Cookie를 추가하면 네트워크 헤더에서 추가된걸 확인 가능함*/
    }
}