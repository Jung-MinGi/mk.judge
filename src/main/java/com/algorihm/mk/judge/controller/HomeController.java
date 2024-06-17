package com.algorihm.mk.judge.controller;

import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.domain.User;
import com.algorihm.mk.judge.service.ProblemService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProblemService service;


    @ModelAttribute
    public static void loginUsernameAddModel(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("name = " + name);
        model.addAttribute("username", (name.equals("anonymousUser")) ? null : name);
    }

    @GetMapping("/problem/detail/{id}")
    public String probDetail(Model model, @PathVariable int id) {
        Problem ret = service.findById(id);
        model.addAttribute("id", ret.getId());
        model.addAttribute("title", ret.getTitle());
        model.addAttribute("content", ret.getContent());
        model.addAttribute("grade", ret.getLevel());
        return "prob_detail";
    }

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("HomeController.home");
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @GetMapping("/join")
    public String join(Model model) {
        return "join";
    }

    @GetMapping("/prob_list")
    public String prob(Model model) {
        System.out.println("HomeController.prob");
        return "prob_list";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager(Model model) {
        System.out.println("HomeController.manager");
        return "manager";
    }

    @GetMapping("/myPage")
    public String myPage(Model model) {
        System.out.println("HomeController.myPage");
        return "myPage";
    }

    @GetMapping("/ex")
    public void jsonError() {
        throw new RuntimeException("오늘 스쿼트 조진다.");
    }
}
