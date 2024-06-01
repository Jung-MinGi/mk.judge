package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.UserRepository;
import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void join(LoginDto loginDto) throws JsonProcessingException {
        User user = new User();
        user.setUsername(loginDto.getUsername());
        user.setPassword(loginDto.getPassword());
        user.setLevel(Level.BRONZE);
        userRepository.join(user);
    }

    @Override
    public ArrayList<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public boolean doubleCheck(String username) {
        return userRepository.doubleCheck(username);
    }
}
