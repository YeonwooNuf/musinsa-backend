package com.example.musinsabackend.controller.admin;

import com.example.musinsabackend.dto.CouponDto;
import com.example.musinsabackend.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/coupons")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class AdminCouponController {

    @Autowired
    private CouponService couponService;

    // ✅ 쿠폰 등록
    @PostMapping("/create")
    public ResponseEntity<?> createCoupon(@RequestBody CouponDto couponDto) {
        try {
            couponService.createCoupon(couponDto);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "쿠폰이 성공적으로 등록되었습니다."
            ));
        } catch (Exception e) {
            log.error("❌ 쿠폰 등록 중 오류 발생: ", e);
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "쿠폰 등록 중 문제가 발생했습니다."
            ));
        }
    }

    // ✅ 전체 쿠폰 목록 조회 (관리자용)
    @GetMapping("/list")
    public ResponseEntity<List<CouponDto>> getAllCoupons() {
        List<CouponDto> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    // ✅ 유효한 쿠폰 조회
    @GetMapping("/valid")
    public ResponseEntity<List<CouponDto>> getValidCoupons() {
        List<CouponDto> validCoupons = couponService.getValidCoupons();
        return ResponseEntity.ok(validCoupons);
    }

    // ✅ 만료된 쿠폰 조회
    @GetMapping("/expired")
    public ResponseEntity<List<CouponDto>> getExpiredCoupons() {
        List<CouponDto> expiredCoupons = couponService.getExpiredCoupons();
        return ResponseEntity.ok(expiredCoupons);
    }

    // ✅ 수동 지급 쿠폰 목록 조회 API
    @GetMapping("/manual")
    public ResponseEntity<List<CouponDto>> getManualCoupons() {
        List<CouponDto> manualCoupons = couponService.getManualCoupons();
        return ResponseEntity.ok(manualCoupons);
    }

    // ✅ 쿠폰 수정
    @PutMapping("/update/{couponId}")
    public ResponseEntity<?> updateCoupon(@PathVariable Long couponId, @RequestBody CouponDto couponDto) {
        try {
            couponService.updateCoupon(couponId, couponDto);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "쿠폰이 성공적으로 수정되었습니다."
            ));
        } catch (Exception e) {
            log.error("❌ 쿠폰 수정 중 오류 발생: ", e);
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "쿠폰 수정 중 문제가 발생했습니다."
            ));
        }
    }

    // ✅ 쿠폰 삭제
    @DeleteMapping("/{couponId}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long couponId) {
        try {
            couponService.deleteCoupon(couponId);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "쿠폰이 성공적으로 삭제되었습니다."
            ));
        } catch (Exception e) {
            log.error("❌ 쿠폰 삭제 중 오류 발생: ", e);
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "쿠폰 삭제 중 문제가 발생했습니다."
            ));
        }
    }
}
