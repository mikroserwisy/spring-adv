package pl.training.shop.orders.payments;

import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.streotype.Adapter;
import pl.training.shop.orders.Payment;
import pl.training.shop.orders.PaymentsService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Adapter
class PaymentsServiceAdapter implements PaymentsService {

    @Override
    public Optional<Payment> pay(Long requestId, FastMoney value, Map<String, String> properties) {
        return Optional.of(new Payment(UUID.randomUUID().toString(), "CONFIRMED"));
    }

}
