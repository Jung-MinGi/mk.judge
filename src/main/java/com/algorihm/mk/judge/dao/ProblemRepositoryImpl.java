package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.*;
import com.algorihm.mk.judge.mybatis.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ProblemRepositoryImpl implements ProblemRepository{
    private final ProblemMapper mapper;
    @Override
    public void registerProb(Problem problem) {
        mapper.registerProb(problem);
    }

    @Override
    public Problem findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public ArrayList<Problem> getProblem(OptionAndPage li) {

        return mapper.getProblem(li);
    }

    @Override
    public ArrayList<Problem> findByTitle(String title) {
        return null;
    }


    @Override
    public ArrayList<Level> getLevel() {
        return mapper.getLevel();
    }

    @Override
    public int getCount(OptionAndPage li) {
        return mapper.getCount(li);
    }

    @Override
    public ArrayList<Solved> getSolvedAndCategory(String username) {
        return mapper.getSolvedAndCategory(username);
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public ArrayList<Problem> solvedProblems(String username) {
        return mapper.solvedProblems(username);
    }

    @Override
    public ArrayList<Map<String,Object>> getRank() {
        return mapper.getRank();
    }

    @Override
    public SolvedCheck solvedCheck(SolvedCheck solvedCheck) {
        return mapper.solvedCheck(solvedCheck);
    }

    @Override
    public void updateSolved(SolvedCheck solvedCheck) {
        mapper.updateSolved(solvedCheck);
    }
}
