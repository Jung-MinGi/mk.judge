package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.UserRepository;
import com.algorihm.mk.judge.domain.CustomUserDetails;
import com.algorihm.mk.judge.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User ret = repository.findByUsername(username);
        if (ret != null) {
            return new CustomUserDetails(ret);
        }
        System.out.println("id 다름");
        return null;
    }
}
