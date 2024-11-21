package testshop.test_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testshop.test_shop.dto.*;
import testshop.test_shop.service.TestService;

import java.util.List;

@RestController
@RequestMapping("/api/database")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }

    @GetMapping("/member-log")
    public ResponseEntity<List<MemberInfoResponse>> readMemberLog(){
        return ResponseEntity.ok(testService.readAllMember());
    }

    @GetMapping("/purchase-log")
    public ResponseEntity<List<PurchaseLogResponse>> readPurchaseLog(){
        return ResponseEntity.ok(testService.readAllPurchaseLog());
    }

    @GetMapping("/purchase-log/{memberId}")
    public ResponseEntity<List<PurchaseLogResponse>> readPurchaseLog(@PathVariable Long memberId){
        return ResponseEntity.ok(testService.readMemberPurchaseLog(memberId));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> readProducts(){
        return ResponseEntity.ok(testService.readAllProducts());
    }

    @GetMapping("/access-log")
    public ResponseEntity<List<AccessLogResponse>> readAccessLogs(){
        return ResponseEntity.ok(testService.readAllAccessLogs());
    }

    @PutMapping("/check-coupon")
    public ResponseEntity<String> checkCoupon(@RequestBody CouponRequest couponRequest) throws Exception {
        testService.registerCoupon(couponRequest);
        return ResponseEntity.ok("쿠폰이 등록되었습니다.");
    }

    @PutMapping("/add-coupon")
    public ResponseEntity<String> addCoupon(@RequestBody AddCouponRequest addCouponRequest) throws Exception {
        testService.addCoupon(addCouponRequest);
        return ResponseEntity.ok("쿠폰이 추가되었습니다.");
    }

    @GetMapping("/recommend")
    public ResponseEntity<ProductRecommendResponse> getRecommend(){
        return ResponseEntity.ok(testService.getProductRecommend());
    }

    @PostMapping("/recommend")
    public ResponseEntity<?> postRecommend(@RequestBody ProductRecommendRequest productRecommendRequest){
        testService.postRecommend(productRecommendRequest);
        return ResponseEntity.ok("성공");
    }
}
