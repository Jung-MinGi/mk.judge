package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.ProblemRepository;
import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.OptionAndPage;
import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.domain.Solved;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository repository;


    @Override
    public void registerProb(Problem problem) {
        repository.registerProb(problem);
    }

    @Override
    public ArrayList<Problem> getProblem(OptionAndPage li) {
//여기서 만약 로그인 한 상태면 문제 성공여부 조회해서 같이 넘겨야됨
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

    @Override
    public ArrayList<Solved> getSolvedAndCategory(String username) {
        return repository.getSolvedAndCategory(username);
    }

    @Override
    public ArrayList<Problem> solvedProblems(String username) {
        return repository.solvedProblems(username);
    }

    @Override
    public int getRank(String username) {
        ArrayList<Map<String, Object>> rank = repository.getRank();
        int answer=1;
        for (int i = 0; i < rank.size(); i++) {
            String s = (String) rank.get(i).get("username");
            if (s.equals(username)) {
                long r = (long) rank.get(i).get("c");
                if(i==0)break;
                for (int j = i-1; j >=0 ; j--) {
                    if(r!=(long) rank.get(j).get("c"))break;
                    else answer--;
                }
                break;
            }else answer++;
        }
        return answer;
    }
}
