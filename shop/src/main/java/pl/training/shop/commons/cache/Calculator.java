package pl.training.shop.commons.cache;

import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "results", keyGenerator = "customKeyGenerator")
@Service
@Log
public class Calculator {

    @Cacheable(/*cacheNames = "results", condition = "#a > #b"*/)
    public int add(int a, int b) {
        log.info("Calculating sum");
        return a + b;
    }

    @CacheEvict(/*value = "results",*/ allEntries = true)
    public void rest() {
        log.info("Reset");
    }

    @CachePut/*("results")*/
    public int preCalculate(int a, int b) {
        return add(a, b);
    }

}
