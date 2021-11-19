package pl.training.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

@Configuration
public class EventsConfiguration {

    @Bean
    public HandlerMapping handlerMapping(EventsHandler eventsHandler) {
        var endpoints = Map.of("/events", eventsHandler);
        var handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(endpoints);
        return handlerMapping;
    }

}
