package pl.training.shop.payments.processing;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;

@Service
@Log
class JmsPaymentsListener {

    @JmsListener(destination = "Training")
    @SneakyThrows
    void onMessage(Message message) {
        log.info("New payment: " + message.getBody(PaymentDto.class));
    }

}
