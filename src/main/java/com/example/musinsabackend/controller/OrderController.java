package com.example.musinsabackend.controller;

import com.example.musinsabackend.dto.OrderRequestDto;
import com.example.musinsabackend.model.user.User;
import com.example.musinsabackend.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderRequestDto orderRequestDto,
            HttpServletRequest request
    ) {
        // ✅ 필터에서 저장한 userId 추출
        Long userId = (Long) request.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(401).body("인증된 사용자 정보가 없습니다.");
        }


        orderService.saveOrder(orderRequestDto, userId);
        return ResponseEntity.ok("주문 저장 완료");
    }

    @GetMapping("/history")
    public ResponseEntity<?> getOrderHistory(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("인증된 사용자 정보가 없습니다.");
        }

        return ResponseEntity.ok(
                // 주문 목록을 orderService에서 받아서 반환
                // 예: List<OrderResponseDto>
                orderService.getOrdersByUser(userId)
        );
    }
}
