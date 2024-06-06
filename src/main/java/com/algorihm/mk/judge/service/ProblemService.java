package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.OptionAndPage;
import com.algorihm.mk.judge.domain.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface ProblemService {
    void registerProb(Problem problem);
    ArrayList<Problem> getProblem(OptionAndPage li);
    Problem findById(int id);
    ArrayList<Level> getLevel();
    int getCount(OptionAndPage li);

}
