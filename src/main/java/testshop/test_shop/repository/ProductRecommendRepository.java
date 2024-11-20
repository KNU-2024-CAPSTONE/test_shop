package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testshop.test_shop.entity.ProductRecommend;

public interface ProductRecommendRepository extends JpaRepository<ProductRecommend, Long> {
}
