# My Tiny World

## Environment
- `Language`: Java 17
- `Framework`: SpringBoot(lastest version)
- `Persistence Framework`: JPA
- `DB`: free

## Requirements
### 계좌(Account)
- 회원은 계좌를 생성할 수 있다.
- 회원은 계좌를 삭제할 수 있다.
- 회원은 계좌 금액을 수정(입금, 출금)할 수 있다.
    - 계좌가 수정되면 이력을 저장한다.
        - 입출금 금액, 회원, 수정 시간
    - 금액 이외의 정보는 수정할 수 없다.
- 회원은 계좌를 조회할 수 있다.
    - 계좌 정보 및 입출금 내역을 조회할 수 있다.

### 회원(Member)
-  회원을 생성한다.
-  회원을 삭제한다.
    - 보유한 계좌(Account) 정보가 삭제된다.
