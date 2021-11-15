
package pl.training.shop.payments;

import lombok.*;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;
import java.util.Map;

import static lombok.AccessLevel.PACKAGE;

@Data
@Builder
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor(access = PACKAGE)
class PaymentDomain {

    private String id;
    private FastMoney value;
    private Instant timestamp;
    private PaymentStatusDomain status;
    private Map<String, String> properties;

}
