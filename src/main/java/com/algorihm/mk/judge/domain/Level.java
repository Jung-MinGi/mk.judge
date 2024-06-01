package com.algorihm.mk.judge.domain;

public enum Level {
    GOLD(null),SILVER(GOLD),BRONZE(SILVER);
    private Level level;

    Level(Level level) {
        this.level=level;
    }

    public Level nextLevel(){
        return this.level;
    }
}
