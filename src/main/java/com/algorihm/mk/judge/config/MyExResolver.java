package com.algorihm.mk.judge.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class MyExResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            response.getWriter().write("{'aaa':'ㅋㅋㅋ'}");
            return new ModelAndView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
