package com.algorihm.mk.judge.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OptionAndPage {
    ArrayList<Level> list;
    int page;
    int range = Const.RANGE;
}
