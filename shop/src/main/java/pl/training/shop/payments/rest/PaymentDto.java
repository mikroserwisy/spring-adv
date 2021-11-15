package pl.training.shop.payments.rest;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Data
class PaymentDto {

    private String id;
    private FastMoney value;
    private Instant timestamp;
    private PaymentStatusDto status;

}
