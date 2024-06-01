package com.algorihm.mk.judge.controller;

import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProblemController {
private final ProblemService service;
    @GetMapping("/problem/detail/{id}")
    public String probDetail(Model model, @PathVariable int id) {
        Problem ret = service.findById(id);
        model.addAttribute("id", ret.getId());
        model.addAttribute("title", ret.getTitle());
        model.addAttribute("content", ret.getContent());
        model.addAttribute("grade", ret.getGrade());
        return "prob_detail";
    }
}
