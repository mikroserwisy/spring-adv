package pl.training.shop.payments.processing;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Data
class PaymentDto implements Serializable {

    private String requestId;
    private String value;

}
