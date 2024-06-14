package com.algorihm.mk.judge.mybatis.mapper;

import com.algorihm.mk.judge.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    ArrayList<User> getAllUser();
    void join(User user);
    int doubleCheck(String username);
    User findByUsername(String username);
}
