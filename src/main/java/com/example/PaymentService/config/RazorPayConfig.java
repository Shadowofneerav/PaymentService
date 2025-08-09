package com.example.PaymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.client}")
    private String razorpayId;
    @Value("${razorpay.secret}")
    private String razorpaySecret;
    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        System.out.println(razorpayId);
        System.out.println(razorpaySecret);
        return new RazorpayClient(razorpayId,razorpaySecret);
    }
}
