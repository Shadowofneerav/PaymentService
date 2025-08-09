package com.example.PaymentService.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway{
    @Value("${stripe.key}")
    private String apikey;
    @Override
    public String generatePaymentLink(Long amount,String orderId,String phoneNumber,String name,String email) {
        try {

            Stripe.apiKey = apikey;
            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(getPrice(amount).getId())
                                            .setQuantity(1L)
                                            .build()
                            ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl("https://github.com/stripe").build()).build())
                            .build();
            PaymentLink paymentLink = PaymentLink.create(params);

            return paymentLink.getUrl();
        }
        catch (StripeException exception)
        {
            throw new RuntimeException(exception.getMessage());
        }
    }
    private Price getPrice(Long amount)
    {
        try
        {
            Stripe.apiKey = apikey;
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();
            return Price.create(params);
        }
        catch (StripeException exception)
        {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
