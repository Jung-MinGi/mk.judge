package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.Problem;
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
    public ArrayList<Problem> getProblem() {
        return mapper.getProblem();
    }
}
