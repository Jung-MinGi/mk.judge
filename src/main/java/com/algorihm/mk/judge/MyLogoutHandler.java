package com.algorihm.mk.judge;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(name+"님 로그아웃 함");
        SecurityContextHolder.clearContext();
    }
}
