# 스프링 시큐리티 JWT 연습

### JWT 발급
### JWT 유효성 검사
### 시큐리티 Authentication 인가

## Endpoint
+ `POST /api/v1/users/login`
  + reqeust body
     ```json
     {
      "username": "string"
     }
     ```
  api 요청 시 jwt 반환
+ `POST /api/v1/users/me`
  + response body
     ```json
     {
      "username": "string"
     }
     ```
  
   