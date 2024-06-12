package com.algorihm.mk.judge.jwt;

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
        System.out.println("jwtUtils = " + jwtUtils.isExpired(token));
        try {
            if(requestURI.equals("/isExpired") && !jwtUtils.isExpired(token)){
                return;
            }else filterChain.doFilter(request, response);

        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었거나 유효하지 않습니다.");
            response.setStatus(401);
        }

    }
}
