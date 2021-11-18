package pl.training.shop.payments.processing;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.Payment;

import javax.jms.Topic;

import static lombok.AccessLevel.PACKAGE;

@Aspect
@Component
@Log
@RequiredArgsConstructor(access = PACKAGE)
public class JmsPaymentsProcessor {

    private final PaymentsProcessingMapper mapper;
    private final JmsTemplate jmsTemplate;
    private final Topic topic;

    @AfterReturning(value = "execution(* pl.training.shop.payments.ProcessPaymentUseCase.pro*(..))", returning = "payment")
    void onPayment(Payment payment) {
        var paymentDto = mapper.toDto(payment);
        jmsTemplate.send(topic, session -> session.createObjectMessage(paymentDto));
    }

}
