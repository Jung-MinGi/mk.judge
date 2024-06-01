package com.algorihm.mk.judge.dao;

import com.algorihm.mk.judge.domain.Level;
import com.algorihm.mk.judge.domain.User;
import com.algorihm.mk.judge.mybatis.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@MybatisTest
class UserRepositoryImplTest {
    @Autowired
    private UserMapper mapper;
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository = new UserRepositoryImpl(mapper);
    }

    @Test
    @DisplayName("insert test")
    public void test1() {
        User user = new User();
        user.setUsername("aaaa");
        user.setPassword("1212");
        user.setLevel(Level.GOLD);
        userRepository.join(user);

        ArrayList<User> ret = userRepository.getAllUser();
        Assertions.assertThat(ret.size()).isOne();
    }

    @Test
    @DisplayName("create test")
    public void test2() {
        User user = new User();
        user.setUsername("aaaa");
        user.setPassword("1212");
        user.setLevel(Level.GOLD);
        userRepository.join(user);

        ArrayList<User> ret = userRepository.getAllUser();
        Assertions.assertThat(ret.size()).isOne();


        //doubleCheck메서드 test
        boolean b = userRepository.doubleCheck("aaaa");
        Assertions.assertThat(b).isFalse();

        b = userRepository.doubleCheck("jjj");
        Assertions.assertThat(b).isTrue();
    }

    @Test
    @DisplayName("유저 1명 조회 메서드 test")
    public void test3() {
        User user = new User();
        user.setUsername("jung");
        user.setPassword("1234");
        user.setLevel(Level.BRONZE);
        userRepository.join(user);

        User jung = userRepository.getUserByUsername("jung");
        userCompare(user, jung);
    }

    private void userCompare(User user, User ret) {
        Assertions.assertThat(user.getUsername()).isEqualTo(ret.getUsername());
        Assertions.assertThat(user.getPassword()).isEqualTo(ret.getPassword());
        Assertions.assertThat(user.getLevel()).isEqualTo(ret.getLevel());
    }
}