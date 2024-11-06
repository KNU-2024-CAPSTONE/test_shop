package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import testshop.test_shop.dto.ProductResponse;
import testshop.test_shop.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new testshop.test_shop.dto.ProductResponse(" +
            "p.name, AVG(pl.starCount), COUNT(pl), p.postDate) " +
            "FROM Product p " +
            "LEFT JOIN PurchaseLog pl ON p.id = pl.product.id " +
            "GROUP BY p.id")
    List<ProductResponse> findProductStatistics();
}
