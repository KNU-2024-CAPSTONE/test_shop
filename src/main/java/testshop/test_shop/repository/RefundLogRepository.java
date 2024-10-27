package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testshop.test_shop.entity.PurchaseLog;
import testshop.test_shop.entity.RefundLog;

@Repository
public interface RefundLogRepository extends JpaRepository<RefundLog, Long> {
    Boolean existsByPurchaseLogId(Long purchaseLogId);
}
