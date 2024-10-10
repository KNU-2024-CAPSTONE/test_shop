package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testshop.test_shop.entity.CouponLog;

import java.util.Optional;

@Repository
public interface CouponLogRepository extends JpaRepository<CouponLog, Long> {
    Optional<CouponLog> findByMemberIdAndCode(Long memberId, String code);
}
