package com.algorihm.mk.judge.controller.api;

import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.OptionAndPage;
import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        System.out.println("ret = " + ret);
        return ret;
    }
}
