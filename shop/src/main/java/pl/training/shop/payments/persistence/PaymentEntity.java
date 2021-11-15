package pl.training.shop.payments.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import java.time.Instant;
import java.util.Map;

@Table(name = "PAYMENTS")
@Entity
@EqualsAndHashCode(of = "id")
@Data
public class PaymentEntity {

    @Id
    private String id;
    private FastMoney value;
    private Instant timestamp;
    private String status;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PAYMENTS_PROPERTIES", joinColumns = @JoinColumn(name = "PAYMENT_ID"))
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    private Map<String, String> properties;

}
