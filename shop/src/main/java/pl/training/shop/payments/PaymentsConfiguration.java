package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentsConfiguration {

    private static final PaymentsFactory PAYMENTS_FACTORY = new DefaultPaymentsFactory();

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(TimeProvider timeProvider, PaymentsWriter paymentsWriter) {
        return PAYMENTS_FACTORY.create(timeProvider, paymentsWriter);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader) {
        return PAYMENTS_FACTORY.create(paymentsReader);
    }

}
