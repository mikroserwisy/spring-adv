package pl.training.shop.commons.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public ProducerFactory<String, String> producerFactory(@Value("${kafka.server}") String server) {
        var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public NewTopic mainTopic(@Value("${kafka.topic}") String topicName) {
        return TopicBuilder.name(topicName).build();
    }

    @Bean
    public KafkaTemplate<String, String> stringStringKafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
