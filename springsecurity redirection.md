```
        http
                .oauth2Login(auth->auth.loginPage("/login")
                        .successHandler(successHandler));
로그인 페이지를 위 코드처럼 커스텀 한 경우 리디렉션 에러가 발생 
이유 -- 로그인 경로를 열어놓지않아 무한 루프가 발생함
- 해결법
  1. http
                .oauth2Login(auth->auth.loginPage("/login").permitAll()
                        .successHandler(successHandler));

  2. http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/login").permitAll()
                        .anyRequest().authenticated());

나의 로그인 핸들러 경로를 위 두 곳중에 한 곳에 추가해주고 permitAll부여해주면 됨

```
