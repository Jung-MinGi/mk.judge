package com.algorihm.mk.judge.controller;

import com.algorihm.mk.judge.domain.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Enumeration;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto",new LoginDto());
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }
    @GetMapping("/prob_list")
    public String prob() {
        return "prob_list";
    }
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }
}
