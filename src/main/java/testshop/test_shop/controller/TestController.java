package testshop.test_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testshop.test_shop.dto.AccessLogResponse;
import testshop.test_shop.dto.MemberInfoResponse;
import testshop.test_shop.dto.ProductResponse;
import testshop.test_shop.dto.PurchaseInfoResponse;
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
    public ResponseEntity<List<PurchaseInfoResponse>> readPurchaseLog(){
        return ResponseEntity.ok(testService.readAllPurchaseLog());
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
    public ResponseEntity<String> checkCoupon(@RequestBody Long memberId, @RequestBody String code) throws Exception {
        testService.registerCoupon(memberId, code);
        return ResponseEntity.ok("쿠폰이 등록되었습니다.");
    }
}
