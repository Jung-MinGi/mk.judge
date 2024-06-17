package com.algorihm.mk.judge.jwt;

import com.algorihm.mk.judge.domain.CustomUserDetails;
import com.algorihm.mk.judge.domain.User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer")) {//헤더에 토큰 안넣으면 여기에 걸림
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.split(" ")[1];
//        if (jwtUtils.isExpired(token)) {
//            System.out.println("token isExpired");
//            filterChain.doFilter(request, response);
//            return;
//        }

        String username = jwtUtils.getUsername(token);
        String role = jwtUtils.getRole(token);

        User user = new User();
        user.setUsername(username);
        user.setPassword("1111");
        user.setRole(role);
        System.out.println("현재 접속 유저 정보 = " + user);
        System.out.println("token = " + token);
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
