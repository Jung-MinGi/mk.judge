package com.algorihm.mk.judge;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreFilter;

import java.io.IOException;
import java.util.Enumeration;
public class SessionCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // HttpServletRequest 객체에서 HttpSession을 가져옵니다.
        HttpSession session = httpRequest.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        attributeNames.asIterator().forEachRemaining(aa-> System.out.println("aa = " + session.getAttribute(aa)));
        // 세션에서 데이터를 읽어올 수 있습니다.
        System.out.println("******************************");
        chain.doFilter(request,response);
    }
}
