package pl.training.shop.payments;

import jakarta.ejb.Remote;

import java.time.Instant;

@Remote
public interface TimeProvider {

    Instant getTimestamp();

}
