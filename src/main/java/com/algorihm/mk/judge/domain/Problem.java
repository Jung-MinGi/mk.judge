package com.algorihm.mk.judge.domain;

import lombok.Data;

@Data
public class Problem {
    int id;
    String title;
    String content;
    String answer;
    Category category;
    Grade grade;

    public Problem(int id, String title, String content, String answer, Category category, Grade grade) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.category = category;
        this.grade = grade;
    }

    public Problem() {
    }
}
