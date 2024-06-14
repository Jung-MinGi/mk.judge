package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.User;
import com.algorihm.mk.judge.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository{
   private final UserMapper userMapper;
    @Override
    public void join(User user) {
        userMapper.join(user);
    }

    @Override
    public boolean doubleCheck(String username) {
        return userMapper.doubleCheck(username) == 0;
    }

    @Override
    public ArrayList<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
