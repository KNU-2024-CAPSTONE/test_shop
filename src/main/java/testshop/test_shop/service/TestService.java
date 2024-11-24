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
    private final CouponLogRepository couponLogRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final PurchaseLogRepository purchaseLogRepository;
    private final RefundLogRepository refundLogRepository;
    private final ProductRecommendRepository productRecommendRepository;

    public TestService(CouponLogRepository couponLogRepository, MemberRepository memberRepository, ProductRepository productRepository, PurchaseLogRepository purchaseLogRepository, RefundLogRepository refundLogRepository, ProductRecommendRepository productRecommendRepository){
        this.couponLogRepository = couponLogRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.purchaseLogRepository = purchaseLogRepository;
        this.refundLogRepository = refundLogRepository;
        this.productRecommendRepository = productRecommendRepository;
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
        return productRepository.findProductStatistics();
    }

    @Transactional
    public void addCoupon(AddCouponRequest addCouponRequest) throws Exception {
        Member member = memberRepository.findById(addCouponRequest.memberId()).orElseThrow(Exception::new);
        CouponLog couponLog = new CouponLog(CouponLog.Status.NOT_REGISTER, addCouponRequest.code(), addCouponRequest.category(), addCouponRequest.discount(), member);
        couponLogRepository.save(couponLog);
    }

    @Transactional(readOnly = true)
    public ProductRecommendResponse getProductRecommend(){
        List<ProductRecommend> productRecommendList = productRecommendRepository.findAll();
        int total = productRecommendList.size();
        int cartCount = 0;
        int purchaseCount = 0;

        if (total == 0) {
            return new ProductRecommendResponse(0, 0);
        }

        for(ProductRecommend productRecommend:productRecommendList){
            if (productRecommend.isCart()) cartCount++;
            if (productRecommend.isPurchase()) purchaseCount++;
        }

        return new ProductRecommendResponse(
                (int) ((cartCount * 100.0) / total),
                (int) ((purchaseCount * 100.0) / total)
        );
    }

    @Transactional
    public void postRecommend(ProductRecommendRequest productRecommendRequest){
        Member member = memberRepository.findByEmail(productRecommendRequest.email()).get();
        Product product = productRepository.findByName(productRecommendRequest.name()).get();

        ProductRecommend productRecommend = new ProductRecommend(member, product, false, false);
        productRecommendRepository.save(productRecommend);
    }
}
