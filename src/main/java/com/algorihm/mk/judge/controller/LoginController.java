package com.algorihm.mk.judge.controller;

import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LoginController {
//    private final ProblemService service;

    //로그인핸들러
    @PostMapping("/login")
    @ResponseBody
    public LoginDto loginProc(@ModelAttribute LoginDto loginDto) {

        return loginDto;
    }

    //회원등록핸들러
//    @PostMapping("/join")
//    @ResponseBody
//    public LoginDto joinProc(@ModelAttribute LoginDto loginDto) {
//
//        return loginDto;
//    }
}
