package com.algorihm.mk.judge.controller.api;

import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.OptionAndPage;
import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class ProblemApiController {
    private final ProblemService service;

    @PutMapping("/problem")//모든 문제 조회
    public ArrayList<Problem> getAllProblem(@RequestBody OptionAndPage opt) {
        return service.getProblem(opt);
    }


    //문제 등록
    @PostMapping("/problem")
    public void registerProb(@RequestBody Problem problem) {
        service.registerProb(problem);
    }

    @GetMapping("/problem/level")//등급 조회
    public ArrayList<Level> getLevel() {
        return service.getLevel();
    }

    @PutMapping("/problem/page")
    public int getCount(@RequestBody OptionAndPage li) {
        int ret = service.getCount(li);
        return ret;
    }

    @GetMapping("/problem/solved")
    public ArrayList<Integer> getProbSolved() {
        ArrayList<Problem> problems = service.solvedProblems(SecurityContextHolder.getContext().getAuthentication().getName());
        ArrayList<Integer> list = new ArrayList<>();
        for (Problem problem : problems) {
            list.add(problem.getId());
        }
        return list;
    }

    @PutMapping("/problem/submit")
    public boolean submit(@RequestBody HashMap<String, String> map) {
        return service.checkAnswer(map.get("id"), map.get("answer"));
    }
}
