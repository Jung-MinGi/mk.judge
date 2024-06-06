package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.*;
import com.algorihm.mk.judge.mybatis.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    public ArrayList<Level> getLevel() {
        return mapper.getLevel();
    }

    @Override
    public int getCount(OptionAndPage li) {
        return mapper.getCount(li);
    }
}
