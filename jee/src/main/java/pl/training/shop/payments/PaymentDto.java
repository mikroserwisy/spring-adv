package pl.training.shop.payments;

import lombok.Value;

import java.time.Instant;

@Value
class PaymentDto {

    String id;
    String value;
    Instant timestamp;
    String status;

}
