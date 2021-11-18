package pl.training.shop.payments;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Training"),
})
@Log
public class PaymentsProcessor implements MessageListener {

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        var payment = message.getBody(PaymentDto.class);
        log.info("New payment: "  + payment);
    }

}
