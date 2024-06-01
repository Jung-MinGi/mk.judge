package com.algorihm.mk.judge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("rank변환테스트")
    public void test() {
        User user = new User();
        user.setLevel(Level.GOLD);
        user.upgradeRank();

        rankCompare(user, null);

        user.setLevel(Level.BRONZE);
        user.upgradeRank();

        rankCompare(user, Level.SILVER);

        user.setLevel(Level.SILVER);
        user.upgradeRank();

        rankCompare(user, Level.GOLD);
    }

    private void rankCompare(User user, Level expected) {
        Assertions.assertThat(user.getLevel()).isEqualTo(expected);

    }

}