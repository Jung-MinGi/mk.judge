package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.*;
import com.algorihm.mk.judge.mybatis.mapper.ProblemMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;

@MybatisTest
class ProblemRepositoryImplTest {
    @Autowired
    private ProblemMapper mapper;

    private ProblemRepository repository;

    @BeforeEach
    void init() {
        repository = new ProblemRepositoryImpl(mapper);
    }

    @Test
    @DisplayName("findById")
    public void test1() {
        check(new Problem(1, "test3", "testContent3", "testAnswer3", Category.BRUTEFORCE, Level.SILVER, false), repository.findById(1));
    }

    @Test
    @DisplayName("getSolvedAndCategory")
    public void test2() {
        ArrayList<Solved> root = repository.getSolvedAndCategory("root");
        for (Solved solved : root) {
            Assertions.assertThat(solved.getCategory()).isInstanceOf(Category.class);
            Assertions.assertThat(solved.getCnt()).isInstanceOf(Integer.class);
        }
    }

    @Test
    @DisplayName("delete")
    public void test3() {
        repository.delete(1);
        Problem ret = repository.findById(1);
        Assertions.assertThat(ret.getRemoved()).isTrue();
    }

    @Test
    @DisplayName("getProblem")
    public void test4() {
        repository.delete(1);
        repository.delete(3);
        OptionAndPage optionAndPage = new OptionAndPage();
        optionAndPage.setPage(1);
        ArrayList<Problem> problem = repository.getProblem(optionAndPage);
        for (Problem x : problem) {
            Assertions.assertThat(x.getRemoved()).isFalse();
        }
    }

    @Test
    @DisplayName("solvedProblems(String username)")
    public void test5() {
        ArrayList<Problem> root = repository.solvedProblems("root");
        for (Problem problem : root) {
            Assertions.assertThat(problem.getRemoved()).isFalse();

        }
    }

    @Test
    @DisplayName("getrank")
    public void test6() {
        ArrayList<Map<String, Object>> rank = repository.getRank();
        int answer=1;
        for (int i = 0; i < rank.size(); i++) {
            System.out.println(rank.get(i).get("C"));
            String s = (String) rank.get(i).get("USERNAME");
            if (s.equals("admin")) {
                long r = (long) rank.get(i).get("C");
                if(i==0)break;
                for (int j = i-1; j >=0 ; j--) {
                    if(r!=(long) rank.get(j).get("C"))break;
                    else answer--;
                }
                break;
            }else answer++;
        }
        Assertions.assertThat(answer).isEqualTo(1);
    }

    private void check(Problem problem, Problem expected) {
        Assertions.assertThat(problem.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(problem.getTitle()).isEqualTo(expected.getTitle());
        Assertions.assertThat(problem.getContent()).isEqualTo(expected.getContent());
        Assertions.assertThat(problem.getCategory()).isEqualTo(expected.getCategory());
        Assertions.assertThat(problem.getLevel()).isEqualTo(expected.getLevel());
    }
}