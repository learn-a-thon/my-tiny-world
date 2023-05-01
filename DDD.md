### DDD(Domain Driven Design) - 도메인 주도 설계
#### 특징
- 도메인 모델과 로직에 집중
- 보편적 언어 사용
- 도메인과 코드가 함께 움직임

Bounded Context : 범위를 구분해놓은 하위 도메인
- 인증
- 검색

Context Map : Bounded Context들의 관계
- 인증 <- 검색

Aggregate : 데이터 라이프 사이클의 집합 (Entity)
- 오로지 Root Entity를 통해서만 접근이 가능함

#### DDD의 전략적 설계
1. 문제 공간 : 핵심 하위 도메인 식별 
   도메인 전문가, 개발자들이 모여 유비쿼터스 언어를 통해 의사소통을 하여 도메인을 식별
- 도서 검색 API -> 인증, 도서 검색
- 인증 Bounded-Context (사용자, 인증키)
- 도서 검색 Bounded-Context (도서, 검색)

2. 해결 공간 : Context Map
- Bounded Context 간의 관계 정의

* Bounded Context 별 전술 설정
1. 인증 Bounded-Context 
- 사용자 : CRUD
- 접근토큰 : Model-Driven

2. 도서 검색 Bounded-Context 
- 도서 : CRUD
- 검색 : Model-Driven

* Context-Map 
- 인증 <-- 도서 검색
- 인증 ( 사용자 <-- 접근토큰 )
- 인증 ( 사용자 <-- 인증키 )
- 인증 ( 인증키 <-- 접근토큰 )
- 검색 ( 도서 <-- 검색 )

#### 레이어
- Presentation : 사용자의 요청을 해석하고 응답을 책임진다.
- Application(Use-case) : 비즈니스 로직을 정의해서 도메인 계층과 인프라 계층을 연결해주는 역할
    - 도메인 개체를 어떻게 사용할지, 직접 사용
    - 정보를 많이 가져서는 안되며, 실제 데이터 변화는 도메인 계층에 위임해야 한다.
    - 트랜잭션 관리, DTO 변환, 모듈간의 연계
- Domain : 비즈니스 규칙과 정보에 대한 실제 도메인 정보를 가지고 모든 것을 책임지는 계층
    - 도메인의 핵심은 "상태제어"
- Infra : 상위 계층을 지원하는 일반화된 기술적 기능을 제공하는 계층
    - JPA 코드 또한, DB 접근하는 것임으로 인프라 계층에 속함
    - 외부 시스템을 호출하는 역할에 주로 쓰이고, 얻어온 데이터를 도메인 계층으로 전달하는 것까지가 임무
