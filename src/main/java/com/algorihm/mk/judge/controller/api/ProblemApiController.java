package com.algorihm.mk.judge.controller.api;

import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.domain.User;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class ProblemApiController {
    private final ProblemService service;
    @GetMapping("/problem")//모든 문제 조회
    public ArrayList<Problem> getAllProblem(){
        return service.getProblem();
    }
    @GetMapping("/problem/{id}")//특정 문제 조회
    public String getProblem(@PathVariable String id){
        return null;
    }

    //문제 등록
    @PostMapping("/problem")
    public void registerProb(@RequestBody Problem problem){
        System.out.println(problem);
        service.registerProb(problem);
    }

}
