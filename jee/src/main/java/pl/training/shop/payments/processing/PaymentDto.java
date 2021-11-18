package pl.training.shop.payments.processing;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String requestId;
    private String value;

}
