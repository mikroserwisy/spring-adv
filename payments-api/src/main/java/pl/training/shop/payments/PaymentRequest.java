package pl.training.shop.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Long requestId;
    private FastMoney value;
    private Map<String, String> properties;

}
