package com.example.musinsabackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long productId;
    private String productName;
    private String productImage;
    private int quantity;
    private int price;
    private boolean reviewWritten; // ✅ 리뷰 작성 여부
    private String color;
    private String size;
}
