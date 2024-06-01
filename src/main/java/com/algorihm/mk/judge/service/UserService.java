package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

public interface UserService {
    void join(LoginDto loginDto) throws JsonProcessingException;
    ArrayList<User> getAllUser();

    boolean doubleCheck(String username);
}
