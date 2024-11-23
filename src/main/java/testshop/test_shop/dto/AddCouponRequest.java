package testshop.test_shop.dto;

public record AddCouponRequest(Long memberId, String category, String code, int discount) {
}
