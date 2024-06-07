package com.algorihm.mk.judge.controller.api;

import com.algorihm.mk.judge.domain.LoginDto;
import com.algorihm.mk.judge.domain.User;
import com.algorihm.mk.judge.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class JoinApiController {
    private final UserService service;
    @GetMapping("/join/id")//아이디중복확인 핸들러
    public boolean idDoubleCheck(@RequestParam String username){

        return service.doubleCheck(username);
    }
    @PutMapping("/join")//회원등록 핸들러
    public String joinUser(@RequestBody LoginDto loginDto) throws JsonProcessingException {
        service.join(loginDto);
        return "login";
    }

    @GetMapping("/tmp/getAll")
    public ResponseEntity<ArrayList<User>> get(){
        ArrayList<User> ret = service.getAllUser();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
