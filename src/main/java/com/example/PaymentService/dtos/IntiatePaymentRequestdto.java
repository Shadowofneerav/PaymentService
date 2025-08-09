package com.example.PaymentService.dtos;

import lombok.Data;

@Data
public class IntiatePaymentRequestdto {
    private Long amount;
    private String orderId;
    private String phoneNumber;
    private String name;
    private String email;
}
