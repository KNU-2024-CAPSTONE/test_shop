package testshop.test_shop.dto;

import java.time.LocalDate;

public class ProductResponse {
    private String productName;
    private Double averageStarCount;
    private Long review;
    private LocalDate postDate;


    public ProductResponse(String productName, Double averageStarCount, Long review, LocalDate postDate) {
        this.productName = productName;
        this.averageStarCount = averageStarCount;
        this.review = review;
        this.postDate = postDate;
    }

    public String getProductName() {
        return productName;
    }

    public Double getAverageStarCount() {
        if (averageStarCount != null) {
            return Math.round(averageStarCount * 100.0) / 100.0;  // 두 자리로 반올림
        }
        return null;
    }

    public Long getReview() {
        return review;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    // getters and setters (if needed)
}