package pl.training.shop.commons.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log
@RequiredArgsConstructor
public class CacheExamples implements ApplicationRunner {

    private final Calculator calculator;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Pre calculate: " + calculator.preCalculate(1, 2));

        log.info("Result: " + calculator.add(1, 2));
        log.info("Result: " + calculator.add(1, 2));
        calculator.rest();
        log.info("Result: " + calculator.add(1, 2));
    }

}
