package com.example.PaymentService.paymentgateways;

public interface IPaymentGateway {
    public String generatePaymentLink(Long amount,String orderId,String phoneNumber,String name,String email);
}
