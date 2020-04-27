OAuth2 + JWT using Spring Boot 2 / Spring Security 5
---

Read more http://blog.marcosbarbero.com/centralized-authorization-jwt-spring-boot2/



请求token

curl -u clientId:secret -X POST localhost:9000/oauth/token\?grant_type=password\&username=user\&password=pass

{
  "access_token" : "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc5NTY0NjYsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZmU0NGMxNzAtODI4Ny00MGM5LTgyN2YtMjhkNDk2MzFiNzcxIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.jhuYigC_1bEaNmBw_JhjkpHX1e4r0oGkAs8lukz66zRQmd21odMFkRosVbrFS6DfDwoBKoYuUCQmllf12FApIPiCrFTJIGI9wh4kzfYfm9sWxooOa2D0E1LqBPMpsN5zijgWFnkWp9U2bBK3V0hc-j5VpTjc7_W19ojMtZ15l_J0hECg3oTItM45qaWEGB9Knp0-KEOT91mJeqX1Eqt0BqOaim9o0U8UfUYa93CAYvbngJKc-vAQScmAKzq9XUpqQYu1VNFJrTOnGF9t-6u-Mz5yNWvfvKUNpgS-L2T39rfsAk51hmreVLp99hRYugNxAcfMK-JT5xRgMkpxF3Jo4w",
  "token_type" : "bearer",
  "refresh_token" : "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImZlNDRjMTcwLTgyODctNDBjOS04MjdmLTI4ZDQ5NjMxYjc3MSIsImV4cCI6MTU5MDU0ODE2NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjhjN2ZmMzA5LTQ0YjUtNGM4NS04NDdhLWZiM2Y2NGFlZWYyOCIsImNsaWVudF9pZCI6ImNsaWVudElkIn0.cRnGCW0Q33sXidxxAsBHTF09nCyot3WEsJCB2OOFdD3jeAYme4-lOCrLnR-sHQehjzH-2JsDutu7Ns1723GHYrTVkHunjwVKmCYt64X_x22f4L26_4R7yacB02DPHZzm2HkI76UxcCi3WFywsuCxJyKjpkI1FpyzyE7OtKFv5JnBhoPiIlVW1rhgt0PtqV6KWT5lRnuiFdMMhnK6Xlr9rgi3AKuxuvZ49Lo4pAmwoXnUcAiTOBheG1QKYo64hnfMlwYAig8DS_lDGxitUhuHZ1oi0pHow4Q1ytiODhXjFYaevhU8QoYjqqHdThRPMYpvjxbvYZ0ZYB0HyezsXkFOrQ",
  "expires_in" : 299,
  "scope" : "read write",
  "jti" : "fe44c170-8287-40c9-827f-28d49631b771"
}



请求资源                                                                                                 
curl localhost:9100/me -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc5NTY0NjYsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZmU0NGMxNzAtODI4Ny00MGM5LTgyN2YtMjhkNDk2MzFiNzcxIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.jhuYigC_1bEaNmBw_JhjkpHX1e4r0oGkAs8lukz66zRQmd21odMFkRosVbrFS6DfDwoBKoYuUCQmllf12FApIPiCrFTJIGI9wh4kzfYfm9sWxooOa2D0E1LqBPMpsN5zijgWFnkWp9U2bBK3V0hc-j5VpTjc7_W19ojMtZ15l_J0hECg3oTItM45qaWEGB9Knp0-KEOT91mJeqX1Eqt0BqOaim9o0U8UfUYa93CAYvbngJKc-vAQScmAKzq9XUpqQYu1VNFJrTOnGF9t-6u-Mz5yNWvfvKUNpgS-L2T39rfsAk51hmreVLp99hRYugNxAcfMK-JT5xRgMkpxF3Jo4w"


{
  "authorities" : [ {
    "authority" : "ROLE_USER"
  } ],
  "details" : {
    "remoteAddress" : "127.0.0.1",
    "sessionId" : null,
    "tokenValue" : "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc5NTUzNDAsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMjNmMzgxMzQtZjMwZS00MDdlLWI3OGYtZjc2NDg0NDUwZTJkIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.gIyFwfVS9ZsNYoInbR6vwDjUxPctY1sEF_s5tckwkdl0BBv1ah_mklGoYwystgz8QNrlzf7exzHsUdCH7jthBP5QyZz6xh4kpIrwbJ5lvHHrnmEKP-XEkTQdwmpWkyoMxmzFlWKu6HJzos_EaYafPBCsQZCb_bfxhvvAJZiO_Fl2OFKYaVZti1XmK1-sLCgmjv_UikiJuWCGHHJBi_ur_yhUGovdhOV0MSLhg13-ZnWgNkFQ1vDp_-jvbMu-KY3b2oq3do7GYyrZDro7H8-BwxaEJa0zSf5ljOuBJjCcPIUnPGd31_B916osMQc2ANQt8Bp4wTKjJ8PnEW-s2uDcGw",
    "tokenType" : "Bearer",
    "decodedDetails" : null
  },
  "authenticated" : true,
  "userAuthentication" : {
    "authorities" : [ {
      "authority" : "ROLE_USER"
    } ],
    "details" : null,
    "authenticated" : true,
    "principal" : "user",
    "credentials" : "N/A",
    "name" : "user"
  },
  "credentials" : "",
  "principal" : "user",
  "clientOnly" : false,
  "oauth2Request" : {
    "clientId" : "clientId",
    "scope" : [ "read", "write" ],
    "requestParameters" : {
      "client_id" : "clientId"
    },
    "resourceIds" : [ ],
    "authorities" : [ ],
    "approved" : true,
    "refresh" : false,
    "redirectUri" : null,
    "responseTypes" : [ ],
    "extensions" : { },
    "grantType" : null,
    "refreshTokenRequest" : null
  },
  "name" : "user"
}




