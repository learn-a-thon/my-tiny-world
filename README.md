# My Tiny World

## Summary
익숙해진 습관, 환경을 벗어나 작은 변화를 만들고 도전해보는 경험을 만든다.


## Environment
- `Language`: Java 17
- `Framework`: SpringBoot(lastest version)
- `Persistence Framework`: free(ex, JPA, Mybatis, JDBC, ...)
- `DB`: free


## Rules
- 특정 환경, 기술에 종속되지 않는 **확장 가능하고 유연한 애플리케이션**을 개발한다.
- 스프링의 핵심 철학을 생각하며 스프링을 더 잘 쓰기 위한 방법이 뭔지 고민해본다.
- [**객체 지향 생활 체조 원칙**](https://catsbi.oopy.io/bf003ff6-2912-4714-8ac2-44eeb7becc93)을 준수하기위해 노력한다.
- 데이터 중심 프로그래밍이 아닌 **객체지향 프로그래밍**을 한다.
- [**CQRS**](https://learn.microsoft.com/ko-kr/azure/architecture/patterns/cqrs)를 고려하며 개발한다.
- 다른 개발자가 코드를 봤을때 쉽게 이해할 수 있도록 **클린코드**를 지향하며 개발한다.
- Commit Convention에 정하고 **일관된 Commit Message를 작성**한다.
- **테스트 코드**는 반드시 작성한다.(TDD는 선택 사항)
    - 핵심 기능에 한해서 작성해도된다.
- **API 문서화**(Swagger3, Rest Docs)를 한다.
- 사용하고 있는 기술(라이브러리)이 어떤 특징, 장단점을 가지고 있는지 이해하고 사용한다.


## Requirements
### 검색 API
- 검색
   - 검색 엔진(네이버, 다음, 구글) API 연동을 통한 검색 기능 지원
   - 검색 결과 Sorting 기능
   - 페이지네이션
   - 자체적으로 제공하는 인기 검색어 조회 
     - DB 활용 필요

- 인증
  - 검색 기능을 활용하기 전 Access Token 발급 필요
  - 인증 프로세스 
    - 1. 사용자는 자신의 계정과 비밀번호를 이용하여 자신을 인증하고, **Api Key**를 발급 받음
        - Api Key를 발급 받기 위한 웹페이지가 없어 임시로 구현한 방식
          - 원래는 Api Key를 발급 받기 위해 Request Body에 계정과 비밀번호 정보를 넣는 것은 탈취 당할 위험이 있어 절대로 권장하지 않음
    - 2. 사용자는 Api Key를 활용하여 Jwt Token 발급을 요청하여 Jwt Token을 발급 받음 
    - 3. 사용자는 Jwt Token을 Authorization 헤더에 포함하여 원하는 요청을 보내고, 서버는 Token이 올바른지 체크함
    - 4. 만약 올바른 Token이라면 요청을 처리하고 정상적으로 응답함
  
- api key 발급 
  - api key는 32byte의 랜덤 문자열
  - api key는 꼭 unique 해야함
  - api key는 hash 되어 db에 저장

- access token 발급
  - jwt token payload 
    - 사용자 권한, 사용자 ID 포함
  - 보안상 이유로 POST 요청으로 처리
  - api key를 발급 받은 사용인지 체크, api key가 유효한지 체크한 후 토큰 발급

- Rate limit

## How To Do It
1. [메인 원격 저장소](https://github.com/learn-a-thon/my-tiny-world)의 main 브랜치 기준으로 **본인의 메인 브랜치(ex, gildong) 생성** 
    1) 메인 원격 저장소에는 개인의 메인 브랜치만 존재
2. [메인 원격 저장소](https://github.com/learn-a-thon/my-tiny-world) fork
3. fork된 **개인 원격 저장소**에서 개인 메인 브랜치(gildong) 기준으로 **기능 브랜치(ex, board-list)를 생성**하여 요구사항 구현 시작 
    1) 브랜치 컨벤션을 자유롭게 정의
4. 요구사항 구현이 완료된 경우 `(메인 원격 저장소)gildong <--- (개인 원격 저장소)board-list`로 PR(MR) 요청
5. 구현된 내용이 **메인 원격 저장소**의 본인 메인 브랜치(gildong)에 Merge 된 후에 **개인 원격 저장소**의 개인 메인 브랜치(gildong)를 **메인 원격 저장소**의 개인 메인 브랜치 브랜치(gildong) 기준으로 rebase한 후에 다음 요구사항을 구현

