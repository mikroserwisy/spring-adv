package pl.training.shop;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.training.shop.commons.LocalMoney;
import pl.training.shop.payments.persistence.JpaPaymentsRepository;
import pl.training.shop.payments.persistence.PaymentEntity;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class ShopApplication implements ApplicationRunner {

    private final JpaPaymentsRepository paymentsRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        var payment = new PaymentEntity();
        var id = UUID.randomUUID().toString();
        payment.setId(id);
        payment.setStatus("STARTED");
        payment.setValue(LocalMoney.of(1_000));
        payment.setTimestamp(Instant.now());

        paymentsRepository.save(payment);
        log.info(paymentsRepository.getWithValueGreaterThen(LocalMoney.of(11)).toString());

    }

}
