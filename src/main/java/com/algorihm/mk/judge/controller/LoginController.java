package com.algorihm.mk.judge.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    @Value("${github.client-id}")
    public String clientId;
    @Value("${github.client-secret}")
    public String clientSecret;
//    private final RestTemplate restTemplate;

    @GetMapping("/oauth2/redirect")
    public String githubLogin(@RequestParam String code) {
//        RestTemplate restTemplate = new RestTemplate();
        System.out.println("LoginController.githubLogin+"+code);
//        ResponseEntity<OAuthInfo> response = restTemplate.exchange("https://github.com/login/oauth/access_token",
//                HttpMethod.POST,
//                getAccessToken(code),
//                OAuthInfo.class);
//        String accessToken = response.getBody().getAccessToken();
//        return "redirect:/githubLogin/success?access_token=" + accessToken;
        return "zzzz";
    }
}
