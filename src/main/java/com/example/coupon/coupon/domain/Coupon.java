package com.example.coupon.coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;


@Getter
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    private Long couponNo;
    private Integer goodsNo;
    private String serial;
    private LocalDateTime createdAt;

    private Coupon(Integer goodsNo, String serial){
        this.goodsNo = goodsNo;
        this.serial = serial;
    }

    public static String createSerial() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder generatedString = new StringBuilder(random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());

        for (int i=0;i<4;i++){
            generatedString.append(random.nextInt(10000));
        }

        return generatedString.toString();
    }

    public static Coupon create(Integer goodsNo){
        return new Coupon(goodsNo, createSerial());
    }
}
