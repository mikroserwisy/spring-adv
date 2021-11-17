package pl.training.shop.payments.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.training.shop.commons.LocalMoney;
import pl.training.shop.commons.jpa.SearchCriteria;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static pl.training.shop.commons.jpa.Operator.EQUAL;
import static pl.training.shop.commons.jpa.Operator.MATCH_START;

@Component
@Log
@RequiredArgsConstructor(access = PACKAGE)
class JpaExamples implements ApplicationRunner {

    private final JpaPaymentsRepository paymentsRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var payment = new PaymentEntity();
        var id = UUID.randomUUID().toString();
        payment.setId(id);
        payment.setStatus("STARTED");
        payment.setValue(LocalMoney.of(1_000));
        payment.setTimestamp(Instant.now());
        var property = new PropertyEntity("test", "test");
        payment.setProperties(List.of(property));
        paymentsRepository.save(payment);

        /*var example = new PaymentEntity();
        example.setStatus("STARTED");
        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues();
        var result = paymentsRepository.findAll(Example.of(example, matcher));
       */

        /*var specification = new PaymentsSpecification(Set.of(
                new SearchCriteria("status", "STAR", MATCH_START),
                new SearchCriteria("value", LocalMoney.of(1_000), EQUAL)
        ));
        var result = paymentsRepository.findAll(specification, PageRequest.of(0, 10));

        log.info("Results: " + result.getContent());*/

        var result = paymentsRepository.getCutomById(id);

        System.out.printf(result.toString());
    }

}
