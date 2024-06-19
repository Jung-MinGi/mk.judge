package com.algorihm.mk.judge.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class CheckIsExpiredJwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer")) {//헤더에 토큰 안넣으면 여기에 걸림
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.split(" ")[1];
        String requestURI = request.getRequestURI();
        try {
            jwtUtils.isExpired(token);
            if (!requestURI.equals("/isExpired")) {
                filterChain.doFilter(request, response);
            }else {
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("토큰 정상.");
            }
        } catch (ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}


