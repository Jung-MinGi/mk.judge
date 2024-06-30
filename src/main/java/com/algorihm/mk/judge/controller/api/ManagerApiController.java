package com.algorihm.mk.judge.controller.api;

import com.algorihm.mk.judge.domain.Const;
import com.algorihm.mk.judge.domain.UpdateDto;
import com.algorihm.mk.judge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
public class ManagerApiController {
    private final ProblemService service;

    @GetMapping("/manager")
    public boolean isManager() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = "";
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }

        return role.equals(Const.ADMIN);
    }
    @PutMapping("/manager/update")
    public void update(@RequestBody UpdateDto updateDto){
        System.out.println("글 수정: "+updateDto);
    }
    @DeleteMapping("/manager/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
