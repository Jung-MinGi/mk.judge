package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.Category;
import com.algorihm.mk.judge.domain.Grade;
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

        repository.registerProb(new Problem(1, "test1", "testContent1", "testAnswer1", Category.MATH, Grade.ONE));
        repository.registerProb(new Problem(2, "test2", "testContent2", "testAnswer2", Category.MATH,Grade.ONE));
        repository.registerProb(new Problem(3, "test3", "testContent3", "testAnswer3", Category.MATH,Grade.ONE));
        repository.registerProb(new Problem(4, "test4", "testContent4", "testAnswer4", Category.MATH,Grade.ONE));
        repository.registerProb(new Problem(5, "test5", "testContent5", "testAnswer5", Category.MATH,Grade.ONE));

        list = new ArrayList<>();
        list.add(new Problem(1, "test1", "testContent1", "testAnswer1", Category.MATH,Grade.ONE));
        list.add(new Problem(2, "test2", "testContent2", "testAnswer2", Category.MATH,Grade.ONE));
        list.add(new Problem(3, "test3", "testContent3", "testAnswer3", Category.MATH,Grade.ONE));
        list.add(new Problem(4, "test4", "testContent4", "testAnswer4", Category.MATH,Grade.ONE));
        list.add(new Problem(5, "test5", "testContent5", "testAnswer5", Category.MATH,Grade.ONE));

    }

    @Test
    @DisplayName("문제 등록 메서드")
    public void test1() {
        Problem problem = new Problem();
        problem.setTitle("사칙연산");
        problem.setContent("4+5=");
        problem.setAnswer("9");
        problem.setCategory(Category.MATH);

//        repository.registerProb(problem);
//        Problem byId = repository.findById(1);
//        System.out.println(byId.toString());
    }

    @Test
    @DisplayName("findById")
    public void test2() {

        for (Problem problem : list) {
            check(problem, repository.findById(problem.getId() + 5));
        }
    }

    private void check(Problem problem, Problem expected) {
        Assertions.assertThat(problem.getId()).isEqualTo(expected.getId() - 5);
        Assertions.assertThat(problem.getTitle()).isEqualTo(expected.getTitle());
        Assertions.assertThat(problem.getContent()).isEqualTo(expected.getContent());
        Assertions.assertThat(problem.getCategory()).isEqualTo(expected.getCategory());
        Assertions.assertThat(problem.getGrade()).isEqualTo(expected.getGrade());
    }
}