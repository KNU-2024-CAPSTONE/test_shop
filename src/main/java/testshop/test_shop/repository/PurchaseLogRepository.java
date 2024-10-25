package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testshop.test_shop.entity.PurchaseLog;

import java.util.List;

@Repository
public interface PurchaseLogRepository extends JpaRepository<PurchaseLog, Long> {
    List<PurchaseLog> findAllByOrderByPurchaseTimeDesc();
    List<PurchaseLog> findAllByMemberIdOrderByPurchaseTimeDesc(Long memberId);
}
