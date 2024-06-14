package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.User;

import java.util.ArrayList;

public interface UserRepository {
    void join(User user);

    boolean doubleCheck(String username);

    ArrayList<User> getAllUser();

    User findByUsername(String username);
}
