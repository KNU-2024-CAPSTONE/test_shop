package testshop.test_shop.dto;

import java.time.LocalDate;

public record MemberInfoResponse(Long id, String email, String gender, int age, LocalDate registerDate) {
}
