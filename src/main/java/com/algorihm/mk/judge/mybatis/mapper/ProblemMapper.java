package com.algorihm.mk.judge.mybatis.mapper;

import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.OptionAndPage;
import com.algorihm.mk.judge.domain.Pagination;
import com.algorihm.mk.judge.domain.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Mapper
public interface ProblemMapper {
    void registerProb(Problem problem);
    Problem findById(int id);
    ArrayList<Problem> getProblem(OptionAndPage li);
    ArrayList<Level> getLevel();
int getCount(OptionAndPage li);
}
