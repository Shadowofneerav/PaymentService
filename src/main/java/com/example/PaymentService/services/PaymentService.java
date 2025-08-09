package com.example.PaymentService.services;

import com.example.PaymentService.paymentgateways.IPaymentGateway;
import com.example.PaymentService.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;
    public String intiatePayment(Long amount,String orderId,String phoneNumber,String name,String email)
    {
        IPaymentGateway iPaymentGateway = paymentGatewayChooserStrategy.getRazorPaymentGateway();
        return iPaymentGateway.generatePaymentLink(amount,orderId,phoneNumber,name,email);
    }
}
