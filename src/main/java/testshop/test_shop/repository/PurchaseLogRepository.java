package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testshop.test_shop.entity.PurchaseLog;

@Repository
public interface PurchaseLogRepository extends JpaRepository<PurchaseLog, Long> {
}
