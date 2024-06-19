package com.algorihm.mk.judge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDto {
    int id;
    String title;
    String content;
    String answer;

}
