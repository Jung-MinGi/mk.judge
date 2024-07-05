package com.algorihm.mk.judge.service;

import com.algorihm.mk.judge.dao.UserRepository;
import com.algorihm.mk.judge.domain.CustomUserDetails;
import com.algorihm.mk.judge.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/*
oauth2기반 로그인 ,jwt기반 로그인 처리 담당
 */
@Service
@RequiredArgsConstructor
public class MyCustomLoginService extends DefaultOAuth2UserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("oauth2login service success");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        return oAuth2User;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("jwtLogin service success");

        User ret = repository.findByUsername(username);
        if (ret != null) {
            return new CustomUserDetails(ret);
        }
        System.out.println("id 다름");
        return null;
    }
}
