package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ProblemRepository{
        void registerProb(Problem problem);
        Problem findById(int id);
        ArrayList<Problem> getProblem(OptionAndPage li);
        ArrayList<Problem> findByTitle(String title);
        ArrayList<Level> getLevel();
        int getCount(OptionAndPage li);

        ArrayList<Solved> getSolvedAndCategory(String username);
        void delete(int id);
        ArrayList<Problem> solvedProblems(String username);

        ArrayList<Map<String,Object>> getRank();
        SolvedCheck solvedCheck(SolvedCheck solvedCheck);
        void updateSolved(SolvedCheck solvedCheck);

}
