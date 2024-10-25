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

API
1. GET /api/database/member-log : 회원 정보를 가져온다.
   - response : (String email, String gender, int age, LocalDate registerDate) 값을 리스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
2. GET /api/database/purchase-log : 구매 정보를 가져온다.
   - (String email, String gender, int age, Product product(String category, String name, int price), int quantity, int starCount, int totalPrice, LocalDateTime purchaseTime) 값을 리스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
   - starCount : 1 ~ 5 사이
   - totalPrice : price * quantity
3. GET /api/database/purchase-log/{memberId} : 특정 사용자의 구매 정보를 가져온다.
   - (String email, String gender, int age, Product product(String category, String name, int price), int quantity, int starCount, int totalPrice, LocalDateTime purchaseTime) 값을 리스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
   - starCount : 1 ~ 5 사이
   - totalPrice : price * quantity
4. GET /api/database/products : 상품 정보를 가져온다.
   - (String email, String gender, int age, LocalDateTime accessTime) 값을 릿스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
5. GET /api/database/access-log : 접속 기록을 불러온다.
   - (String category, String name) 값을 리스트 형태로 가져온다.
6. PUT /api/database/check-coupon : 쿠폰 지급 내역을 확인한 후 등록한다.
    - requestBody({Long memberId, String code})의 형태로 요청한다.
    - memberId : Member에서의 id
    - code : 쿠폰코드, 12자리 A~Z, 0~9 값으로 이루어져있다.
7. POST /api/database/add-coupon : 쿠폰을 등록한다.
   - requestBody({Long memberId, String category, String code})의 형태로 요청한다.
   - memberId : Member에서의 id
   - category : 상품에 적용 가능한 카테고리, 전체 적용 가능할 경우 null이다.
   - code : 쿠폰코드, 12자리 A~Z, 0~9 값으로 이루어져있다.