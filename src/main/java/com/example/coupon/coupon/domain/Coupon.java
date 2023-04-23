package com.example.coupon.coupon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Coupon {
    private Long couponNo;
    private Integer goodsNo;
    private String serial;
    private LocalDateTime createdAt;

    private Coupon(Integer goodsNo, String serial){
        this.goodsNo = goodsNo;
        this.serial = serial;
    }

    public static Coupon create(Integer goodsNo, String serial){
        return new Coupon(goodsNo, serial);
    }
}
