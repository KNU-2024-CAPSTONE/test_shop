package testshop.test_shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testshop.test_shop.dto.*;
import testshop.test_shop.entity.*;
import testshop.test_shop.repository.*;

import java.util.List;

@Service
public class TestService {
    private final AccessLogRepository accessLogRepository;
    private final CouponLogRepository couponLogRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final PurchaseLogRepository purchaseLogRepository;

    public TestService(AccessLogRepository accessLogRepository, CouponLogRepository couponLogRepository, MemberRepository memberRepository, ProductRepository productRepository, PurchaseLogRepository purchaseLogRepository){
        this.accessLogRepository = accessLogRepository;
        this.couponLogRepository = couponLogRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.purchaseLogRepository = purchaseLogRepository;
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> readAllMember(){
        List<Member> memberList = memberRepository.findAll();

        List<MemberInfoResponse> result = memberList.stream().map(Member::mapToResponse).toList();
        return result;
    }

    @Transactional(readOnly = true)
    public List<PurchaseInfoResponse> readAllPurchaseLog(){
        List<PurchaseLog> purchaseLogList = purchaseLogRepository.findAll();

        List<PurchaseInfoResponse> result = purchaseLogList.stream().map(PurchaseLog::mapToResponse).toList();
        return result;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> readAllProducts(){
        List<Product> productList = productRepository.findAll();

        List<ProductResponse> result = productList.stream().map(Product::mapToRespone).toList();
        return result;
    }

    @Transactional(readOnly = true)
    public List<AccessLogResponse> readAllAccessLogs(){
        List<AccessLog> accessLogList = accessLogRepository.findAll();

        List<AccessLogResponse> result = accessLogList.stream().map(AccessLog::mapToResponse).toList();
        return result;
    }

    @Transactional
    public void registerCoupon(CouponRequest couponRequest) throws Exception {
        CouponLog couponLog = couponLogRepository.findByMemberIdAndCode(couponRequest.memberId(), couponRequest.code()).orElseThrow(Exception::new);
        couponLog.changeStatusToRegistered();
    }
}
