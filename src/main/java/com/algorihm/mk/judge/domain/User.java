package com.algorihm.mk.judge.domain;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Level level;

    public void upgradeRank() {
        this.level = level.nextLevel();
    }
}
