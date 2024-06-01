package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.ProblemRepository;
import com.algorihm.mk.judge.domain.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{
    private final ProblemRepository repository;


    @Override
    public void registerProb(Problem problem) {
        repository.registerProb(problem);
    }

    @Override
    public ArrayList<Problem> getProblem() {
        return repository.getProblem();
    }

    @Override
    public Problem findById(int id) {
        return repository.findById(id);
    }
}
