package testshop.test_shop.dto;

import java.time.LocalDateTime;

public record PurchaseLogResponse(String email, String gender, int age, Product product, int quantity, int starCount, int totalPrice, LocalDateTime purchaseTime, Boolean isRefund) {
    public record Product(String category, String name, int price){}
}
