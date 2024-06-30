package com.algorihm.mk.judge.mybatis.mapper;

import com.algorihm.mk.judge.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface ProblemMapper {
    void registerProb(Problem problem);

    void updateSolved(SolvedCheck solvedCheck);

    Problem findById(int id);

    ArrayList<Problem> getProblem(OptionAndPage li);

    ArrayList<Level> getLevel();

    int getCount(OptionAndPage li);

    ArrayList<Problem> solvedProblems(String username);

    ArrayList<Solved> getSolvedAndCategory(String username);

    ArrayList<Problem> findByTitle(String title);

    void delete(int id);

    ArrayList<Map<String, Object>> getRank();
    SolvedCheck solvedCheck(SolvedCheck solvedCheck);
}
