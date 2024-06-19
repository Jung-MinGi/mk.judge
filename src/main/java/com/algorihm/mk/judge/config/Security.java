package com.algorihm.mk.judge.config;

import com.algorihm.mk.judge.jwt.CheckIsExpiredJwtFilter;
import com.algorihm.mk.judge.jwt.JwtFilter;
import com.algorihm.mk.judge.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final AuthenticationConfiguration configuration;
    private final JwtUtils jwtUtils;

    private final ObjectMapper objectMapper;

    //    @Bean//세션기반
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/prob_list").authenticated()
//                        .requestMatchers("/css/**","/js/**","/images/**").permitAll()
//                        .anyRequest().permitAll());
//
//
//        http.formLogin(formLogin -> formLogin
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/").permitAll());
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
                .csrf(AbstractHttpConfigurer::disable);

        //Form 로그인 방식 disable
        http
                .formLogin(AbstractHttpConfigurer::disable);

        //http basic 인증 방식 disable
        http
                .httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/login", "/", "/join").permitAll()
//                        .requestMatchers("/prob_list").hasRole("ADMIN")
                        .requestMatchers("/manager").hasRole("ADMIN")
//                        .requestMatchers("/prob_list").authenticated()
                        .anyRequest().permitAll());

        http
                .addFilterBefore(new JwtFilter(jwtUtils), LoginFilter.class);

        http
                .addFilterBefore(new CheckIsExpiredJwtFilter(jwtUtils), JwtFilter.class);

        http
                .addFilterAfter(new LoginFilter(authenticationManager(configuration), jwtUtils, objectMapper), UsernamePasswordAuthenticationFilter.class);
        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
