
![image](https://github.com/Jung-MinGi/mk.judge/assets/118701129/9f5c6d12-363c-4936-aede-a346d10f9ade)

출처 https://www.youtube.com/watch?v=JQJVHcI7Rzo&list=PLJkjrxxiBSFALedMwcqDw_BPaJ3qqbWeB&index=4


```
1. client req http://localhost:8080/oauth2/authorization/google //클라이언트에서 링크 요청

2. OAuth2AuthorizationRequestRedirectFilter가 잡아서 소셜로그인 페이지로 이동시킴

3. 클라이언트가 정보 입력후 요청을 보내면 OAuth2LoginAuthenticationFilter이 필터가 요청을 가로챔

 3-1. OAuth2LoginAuthenticationProvider
   3-1-1. 구글 인증서버 접근해서 파라미터로 넘어온 code검증후 토큰 발급
  ---> OAuth2AccessToken accessToken = authorizationCodeAuthenticationToken.getAccessToken();
   3-1-2. accesstoken으로 구글 리소스 서버에 접근 후 유저 정보 획득
   ---> Map<String, Object> additionalParameters = authorizationCodeAuthenticationToken.getAdditionalParameters();
   3-1-3. 아래코드는 받아온 유저정보를 내 웹 db에 저장하는 로직이며, userService와 OAuth2User만 구현해주어야 함
   ----> OAuth2User oauth2User = this.userService.loadUser(new OAuth2UserRequest(
				loginAuthenticationToken.getClientRegistration(), accessToken, additionalParameters));





```
 -->
