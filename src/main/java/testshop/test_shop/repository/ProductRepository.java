package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testshop.test_shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
