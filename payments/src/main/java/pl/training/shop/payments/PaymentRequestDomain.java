package pl.training.shop.payments;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Data
class PaymentRequestDomain {

    private Long id;
    private FastMoney value;
    private Map<String, String> properties;

}
