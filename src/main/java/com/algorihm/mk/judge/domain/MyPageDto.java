package com.algorihm.mk.judge.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MyPageDto {
    private User user;
    private ArrayList<String> labels;
    private ArrayList<String> solved;
    private ArrayList<String> backGroundColor;
    private ArrayList<Problem> solvedProblems;

}
