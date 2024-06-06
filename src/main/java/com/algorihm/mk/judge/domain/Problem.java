package com.algorihm.mk.judge.domain;

import lombok.Data;

@Data
public class Problem {
    int id;
    String title;
    String content;
    String answer;
    Category category;
    Level level;

    public Problem(int id, String title, String content, String answer, Category category, Level level) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.category = category;
        this.level = level;
    }

    public Problem() {
    }
}
