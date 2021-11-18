package pl.training.shop.payments;

import jakarta.ejb.Stateless;

import java.time.Instant;

@Stateless
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
