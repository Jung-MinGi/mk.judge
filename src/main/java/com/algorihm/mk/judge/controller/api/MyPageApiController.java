package com.algorihm.mk.judge.controller.api;

import com.algorihm.mk.judge.domain.*;
import com.algorihm.mk.judge.service.ProblemService;
import com.algorihm.mk.judge.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@RequiredArgsConstructor
@RestController
public class MyPageApiController {
    private final UserService userService;
    private final ProblemService problemService;

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exHandler(ExpiredJwtException e) {
        System.out.println("MyPageApiController.exHandler");
        return "zzzzzz";
    }

    @GetMapping("/api/myPage")
    public MyPageDto myPage() {

        MyPageDto myPageDto = new MyPageDto();
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<String> solved = new ArrayList<>();
        ArrayList<String> backGroundColor = new ArrayList<>();
        ArrayList<Problem> solvedProblems = problemService.solvedProblems(user.getUsername());
        int totalProblems = problemService.getCount(new OptionAndPage());
        ArrayList<Solved> li = problemService.getSolvedAndCategory(user.getUsername());
        for (Solved x : li) {
            labels.add(String.valueOf(x.getCategory()));
            solved.add(String.valueOf(x.getCnt()));
            totalProblems -= x.getCnt();
        }

        labels.add("남은 문제");
        solved.add(String.valueOf(totalProblems));
        myPageDto.setUser(user);
        myPageDto.setLabels(labels);
        myPageDto.setSolved(solved);
        myPageDto.setSolvedProblems(solvedProblems);
        makeColor(labels.size() - 1, backGroundColor);
        backGroundColor.add("rgb(192, 192, 192)");
        myPageDto.setBackGroundColor(backGroundColor);
        return myPageDto;
    }

    private void makeColor(int len, ArrayList<String> list) {

        Random random = new Random();

        for (int i = 0; i < len; i++) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            list.add(String.format("rgb(%d, %d, %d)", r, g, b));
        }
    }
}
