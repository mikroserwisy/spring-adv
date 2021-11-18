package pl.training.shop.payments.processing;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentDto implements Serializable {

    private String requestId;
    private String value;

}
