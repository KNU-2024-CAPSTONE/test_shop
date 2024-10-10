package testshop.test_shop.dto;

import java.time.LocalDate;

public record MemberInfoResponse(String email, String gender, int age, LocalDate registerDate) {
}
