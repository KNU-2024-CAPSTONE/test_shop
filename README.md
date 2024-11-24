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

CouponLog : 쿠폰의 정보, (id(PK), status, code, category, discount, member_id(Fk))를 가진다.
- id : 인덱스
- status : 쿠폰의 등록 정보(등록 전, 등록 완료, 사용 완료)
- code : 쿠폰번호
- category: 적용되는 카테고리
- discount : 할인율
- member_id : Member의 인덱스

PurchaseLog : 구매기록, (id(PK), purchaseTime, member_id(FK), product_id(FK), quantity, starCount)를 가진다.
- id : 인덱스
- purchaseTime : 구매 시간
- member_id : Member의 인덱스
- product_id : Product의 인덱스
- quantity : 구매 수량
- starCount : 별점

RefundLog : 환불 기록, (id(PK), purchase_log_id(FK))를 가진다.
- id : 인덱스
- purchase_log_id : 환불한 구매기록의 인덱스

ProductRecommend : 상품 추천 기록, (id(PK), member_id(FK), product_id(FK), isCart, isPurchase)를 가진다.
- id : 인덱스
- member_id : Member의 인덱스
- product_id : Product의 인덱스
- isCart : 장바구니에 담았는지 여부
- isPurchase : 구매 여부

임의의 데이터 목록
- Member : 무작위 100개
- Product : 무작위 100개
- AccessLog : 무작위 100개
- PurchaseLog : 무작위 1000개
- RefundLog : 무작위 200개
- ProductRecommend : 무작위 100개
API
1. GET /api/database/member-log : 회원 정보를 가져온다.
   - response : (String email, String gender, int age, LocalDate registerDate) 값을 리스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
2. GET /api/database/purchase-log : 구매 정보를 가져온다.
   - (String email, String gender, int age, Product product(String category, String name, int price), int quantity, int starCount, int totalPrice, LocalDateTime purchaseTime) 값을 리스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
   - starCount : 1 ~ 5 사이
   - totalPrice : price * quantity
   - isRefund : 환불 여부, true/false
3. GET /api/database/purchase-log/{memberId} : 특정 사용자의 구매 정보를 가져온다.
   - (String email, String gender, int age, Product product(String category, String name, int price), int quantity, int starCount, int totalPrice, LocalDateTime purchaseTime) 값을 리스트 형태로 가져온다.
   - gender : "male" / "female" 중 하나이다.
   - starCount : 1 ~ 5 사이
   - totalPrice : price * quantity
   - isRefund : 환불 여부, true/false
4. GET /api/database/products : 상품 정보를 가져온다.
   - (String productName, Double averageStarCount, Long review, LocalDate postDate) 값을 리스트 형태로 가져온다.
   -  productName : 상품의 이름이다.
   -  averageStarCount : 상품의 평균 별점으로, 소수점 두 자리까지만 반환한다.
   -  review : 리뷰 수이다.
   -  postDate : 제품 등록일이다.
5. POST /api/database/add-coupon : 쿠폰을 등록한다.
   - requestBody({Long memberId, String category, String code, int discount})의 형태로 요청한다.
   - memberId : Member에서의 id
   - category : 상품에 적용 가능한 카테고리, 전체 적용 가능할 경우 null이다.
   - code : 쿠폰코드, 12자리 A-Z, 0-9 값으로 이루어져있다.
   - discount : 할인율, 0 ~ 100 사이의 정수
6. GET /api/database/recommend : 추천된 상품의 장바구니 담은 비율 및 구매 비율을 조회한다.
   - cartPercent : 장바구니에 담은 비율(정수)
   - purchasePercent : 구매한 비율(정수)
7. POST /api/database/recommend : 추천된 상품을 저장한다.
   - requestBody({String email, String name})의 형태로 요청한다.
   - email : 사용자의 이메일 주소
   - name : 상품의 이름