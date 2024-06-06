package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.ProblemRepository;
import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.OptionAndPage;
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
    public ArrayList<Problem> getProblem(OptionAndPage li) {
//여기서 만약 로그인 한 상태면 문제 성ㅇ공여부 조회해서 같이 넘겨야됨
        return repository.getProblem(li);
    }


    @Override
    public Problem findById(int id) {
        return repository.findById(id);
    }

    @Override
    public ArrayList<Level> getLevel() {
        return repository.getLevel();
    }

    @Override
    public int getCount(OptionAndPage li) {
        return repository.getCount(li);
    }
}
