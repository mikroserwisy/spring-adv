package pl.training.shop.payments;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;
import java.util.Map;

@Data
public class Payment {

    private String id;
    private FastMoney value;
    private Instant timestamp;
    private PaymentStatus status;
    private Map<String, String> properties;

}
