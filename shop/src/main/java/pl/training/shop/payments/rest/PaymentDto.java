package pl.training.shop.payments.rest;

import lombok.Data;

import java.time.Instant;

@Data
class PaymentDto {

    private String id;
    private String value;
    private Instant timestamp;
    private PaymentStatusDto status;

}
