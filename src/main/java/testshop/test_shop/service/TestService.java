package testshop.test_shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testshop.test_shop.dto.*;
import testshop.test_shop.entity.*;
import testshop.test_shop.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    private final AccessLogRepository accessLogRepository;
    private final CouponLogRepository couponLogRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final PurchaseLogRepository purchaseLogRepository;
    private final RefundLogRepository refundLogRepository;

    public TestService(AccessLogRepository accessLogRepository, CouponLogRepository couponLogRepository, MemberRepository memberRepository, ProductRepository productRepository, PurchaseLogRepository purchaseLogRepository, RefundLogRepository refundLogRepository){
        this.accessLogRepository = accessLogRepository;
        this.couponLogRepository = couponLogRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.purchaseLogRepository = purchaseLogRepository;
        this.refundLogRepository = refundLogRepository;
    }

    public PurchaseLogResponse mapToResponse(PurchaseLog purchaseLog){
        boolean isRefunded = refundLogRepository.existsByPurchaseLogId(purchaseLog.getId());
        return new PurchaseLogResponse(purchaseLog.getMember().getEmail(),
                purchaseLog.getMember().getGender(),
                purchaseLog.getMember().getAge(),
                new PurchaseLogResponse.Product(purchaseLog.getProduct().getCategory(), purchaseLog.getProduct().getName(), purchaseLog.getProduct().getPrice()),
                purchaseLog.getQuantity(),
                purchaseLog.getStarCount(),
                purchaseLog.getTotalPrice(),
                purchaseLog.getPurchaseTime(), isRefunded);
    }

    @Transactional(readOnly = true)
    public List<MemberInfoResponse> readAllMember(){
        List<Member> memberList = memberRepository.findAll();

        return memberList.stream().map(Member::mapToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PurchaseLogResponse> readAllPurchaseLog(){
        List<PurchaseLog> purchaseLogList = purchaseLogRepository.findAllByOrderByPurchaseTimeDesc();

        return purchaseLogList.stream().map(this::mapToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PurchaseLogResponse> readMemberPurchaseLog(Long memberId){
        List<PurchaseLog> purchaseLogList = purchaseLogRepository.findAllByMemberIdOrderByPurchaseTimeDesc(memberId);

        return purchaseLogList.stream().map(this::mapToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> readAllProducts(){
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(Product::mapToRespone).toList();
    }

    @Transactional(readOnly = true)
    public List<AccessLogResponse> readAllAccessLogs(){
        List<AccessLog> accessLogList = accessLogRepository.findAll();

        return accessLogList.stream().map(AccessLog::mapToResponse).toList();
    }

    @Transactional
    public void registerCoupon(CouponRequest couponRequest) throws Exception {
        CouponLog couponLog = couponLogRepository.findByMemberIdAndCode(couponRequest.memberId(), couponRequest.code()).orElseThrow(Exception::new);
        couponLog.changeStatusToRegistered();
    }

    @Transactional
    public void addCoupon(AddCouponRequest addCouponRequest) throws Exception {
        Member member = memberRepository.findById(addCouponRequest.memberId()).orElseThrow(Exception::new);
        CouponLog couponLog = new CouponLog(CouponLog.Status.NOT_REGISTER, addCouponRequest.code(), addCouponRequest.category(), member);
        couponLogRepository.save(couponLog);
    }
}
