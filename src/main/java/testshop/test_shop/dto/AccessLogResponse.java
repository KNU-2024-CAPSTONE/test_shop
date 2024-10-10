package testshop.test_shop.dto;

import java.time.LocalDateTime;

public record AccessLogResponse(String email, String gender, int age, LocalDateTime accessTime) {
}
