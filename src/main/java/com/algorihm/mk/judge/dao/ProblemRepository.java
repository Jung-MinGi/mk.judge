package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.Problem;

import java.util.ArrayList;

public interface ProblemRepository{
        void registerProb(Problem problem);
        Problem findById(int id);
        ArrayList<Problem> getProblem();

}
