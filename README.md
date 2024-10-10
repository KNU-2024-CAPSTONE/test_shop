쇼핑몰 테스트용 백엔드 서버

Member : 회원정보, (id(PK), email, birthday, register_date, gender)를 가진다.
- id : 인덱스
- email : 회원의 이메일
- birthday : 회원의 생년월일
- register_date : 회원가입 날짜
- gender : 성별

Product : 상품정보, (id(PK), category, name, price)를 가진다.
- id : 인덱스
- category : 상품의 카테고리
- name : 상품명
- price : 상품 가격

AccessLog : 회원의 마지막 접속시간, (id(PK), member_id(FK), access_time)을 가진다.
- id : 인덱스
- member_id : Member의 인덱스
- access_time : 마지막 접속 시간

CouponLog : 쿠폰의 정보, (id(PK), status, code, category, member_id(Fk))를 가진다.
- id : 인덱스
- status : 쿠폰의 등록 정보(등록 전, 등록 완료, 사용 완료)
- code : 쿠폰번호
- category: 적용되는 카테고리
- member_id : Member의 인덱스

PurchaseLog : 구매기록, (id(PK), purchaseTime, member_id(FK), product_id(FK), quantity, starCount)를 가진다.
- id : 인덱스
- purchaseTime : 구매 시간
- member_id : Member의 인덱스
- product_id : Product의 인덱스
- quantity : 구매 수량
- starCount : 별점

임의의 데이터 목록
- Member : 무작위 100개
- Product : 무작위 100개
- AccessLog : 무작위 100개
- PurchaseLog : 무작위 1000개