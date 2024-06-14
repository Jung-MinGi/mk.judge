package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.UserRepository;
import com.algorihm.mk.judge.domain.Const;
import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void join(LoginDto loginDto) throws JsonProcessingException {
        User user = new User();
        user.setUsername(loginDto.getUsername());
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        user.setLevel(Level.BRONZE);
        user.setRole(Const.USER);
        userRepository.join(user);
    }

    @Override
    public ArrayList<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean doubleCheck(String username) {
        if(username.matches(Const.REGEX)) return userRepository.doubleCheck(username);
        return false;
    }

}
