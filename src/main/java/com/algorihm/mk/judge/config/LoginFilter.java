package com.algorihm.mk.judge.config;

import com.algorihm.mk.judge.domain.CustomUserDetails;
import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDto loginDto;
        try {
            ServletInputStream inputStream = request.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            loginDto = objectMapper.readValue(body, LoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails principal = (CustomUserDetails) authResult.getPrincipal();
        if(principal==null){
            response.getWriter().write("아이디 또는 비밀번호가 다릅니다.");
            response.setStatus(401);
            return;
        }
        String username = principal.getUsername();
        String role = "";
        for (GrantedAuthority authority : principal.getAuthorities()) {
            role = authority.getAuthority();
        }
        System.out.println("로그인 성공");
        System.out.println("username = " + username);
        System.out.println("role = " + role);
//        String jwt = jwtUtils.createJwt(username, role, 7 * 24 * 60 * 60 * 1000L);//일주일
//        String jwt = jwtUtils.createJwt(username, role, 30 * 1000L);//30초
        String jwt = jwtUtils.createJwt(username, role, 3600000L);//1시간

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("{\"token\": \"" + jwt + "\"}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("아이디 또는 비밀번호가 다릅니다.");
        response.setStatus(401);
    }
}
