```
client req http://localhost:8080/oauth2/authorization/google //클라이언트에서 링크 요청

OAuth2AuthorizationRequestRedirectFilter가 잡아서 소셜로그인 페이지로 이동시킴

클라이언트가 정보 입력후 OAuth2LoginAuthenticationFilter이 필터가 요청을 가로챔

OAuth2LoginAuthenticationProvider가 구글 리소스 서버 접근후 유저 정보 매치

OAuth2User oauth2User = this.userService.loadUser(new OAuth2UserRequest(
				loginAuthenticationToken.getClientRegistration(), accessToken, additionalParameters));

userService와 OAuth2User만 구현해주면 됨
```
 -->
