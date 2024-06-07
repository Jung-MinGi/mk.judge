package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.Category;
import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.Problem;
import com.algorihm.mk.judge.mybatis.mapper.ProblemMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@MybatisTest
class ProblemRepositoryImplTest {
    @Autowired
    private ProblemMapper mapper;

    private ProblemRepository repository;
    private ArrayList<Problem> list;

    @BeforeEach
    void init() {
        repository = new ProblemRepositoryImpl(mapper);

        repository.registerProb(new Problem(1, "test1", "testContent1", "testAnswer1", Category.MATH,Level.SILVER));
        repository.registerProb(new Problem(2, "test2", "testContent2", "testAnswer2", Category.MATH,Level.SILVER));
        repository.registerProb(new Problem(3, "test3", "testContent3", "testAnswer3", Category.MATH,Level.SILVER));
        repository.registerProb(new Problem(4, "test4", "testContent4", "testAnswer4", Category.MATH,Level.SILVER));
        repository.registerProb(new Problem(5, "test5", "testContent5", "testAnswer5", Category.MATH,Level.SILVER));

        list = new ArrayList<>();
        list.add(new Problem(1, "test1", "testContent1", "testAnswer1", Category.MATH, Level.SILVER));
        list.add(new Problem(2, "test2", "testContent2", "testAnswer2", Category.MATH, Level.SILVER));
        list.add(new Problem(3, "test3", "testContent3", "testAnswer3", Category.MATH, Level.SILVER));
        list.add(new Problem(4, "test4", "testContent4", "testAnswer4", Category.MATH, Level.SILVER));
        list.add(new Problem(5, "test5", "testContent5", "testAnswer5", Category.MATH, Level.SILVER));

    }

    @Test
    @DisplayName("findById")
    public void test2() {
        for (Problem problem : list) {
            check(problem, repository.findById(problem.getId()));
        }
    }

    private void check(Problem problem, Problem expected) {
        Assertions.assertThat(problem.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(problem.getTitle()).isEqualTo(expected.getTitle());
        Assertions.assertThat(problem.getContent()).isEqualTo(expected.getContent());
        Assertions.assertThat(problem.getCategory()).isEqualTo(expected.getCategory());
        Assertions.assertThat(problem.getLevel()).isEqualTo(expected.getLevel());
    }
}