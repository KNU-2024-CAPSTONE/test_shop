package testshop.test_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testshop.test_shop.entity.AccessLog;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
