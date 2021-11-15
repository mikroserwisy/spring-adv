package pl.training.shop.payments.rest;

import lombok.Data;

import java.util.Map;

@Data
class PaymentRequestDto {

    private Long requestId;
    private String value;
    private Map<String, String> additionalInfo;

}
