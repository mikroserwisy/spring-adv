package pl.training.shop.payments.rest;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Data
class PaymentRequestDto {

    private Long requestId;
    private FastMoney value;
    private Map<String, String> additionalInfo;

}
