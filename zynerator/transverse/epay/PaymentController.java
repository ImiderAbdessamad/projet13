package ma.zs.zyn.zynerator.transverse.epay;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    @GetMapping("/create-payment-intent/{amount}")
    public CreatePaymentResponse createPaymentIntent(@PathVariable Long amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount * 10)
                        .setCurrency("usd")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return new CreatePaymentResponse(paymentIntent.getClientSecret());
    }

}
