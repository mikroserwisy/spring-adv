package pl.training.shop.commons.kafka;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Log
@RequiredArgsConstructor
public class KafkaExamples implements ApplicationRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic}")
    @Setter
    private String topicName;

    @KafkaListener(topics = "main", groupId = "training")
    public void onMessage(String message) {
        log.info("New kafka message: " + message);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        kafkaTemplate.send(topicName, "Hello from Kafka");
    }

}
