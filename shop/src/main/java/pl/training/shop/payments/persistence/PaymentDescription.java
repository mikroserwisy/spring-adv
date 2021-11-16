package pl.training.shop.payments.persistence;

import org.springframework.beans.factory.annotation.Value;

interface PaymentDescription {

    String getId();
    String getStatus();
    @Value("#{target.id + ':' + target.status}")
    String getDescription();

}
