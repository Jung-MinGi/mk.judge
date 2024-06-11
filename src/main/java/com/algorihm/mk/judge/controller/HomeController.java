package com.algorihm.mk.judge.controller;

import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProblemService service;

    @ModelAttribute
    private static void loginUsernameAddModel(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
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
        return "prob_list";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager(Model model) {
        System.out.println("HomeController.manager");
        return "manager";
    }



}
