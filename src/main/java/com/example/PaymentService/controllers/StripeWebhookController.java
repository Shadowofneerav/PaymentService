package com.example.PaymentService.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebhookController {

    @PostMapping
    public void listenToStripeEvents(@RequestBody String event)
    {
        System.out.println("Hello from Stripe");
        System.out.println(event);
    }
}
