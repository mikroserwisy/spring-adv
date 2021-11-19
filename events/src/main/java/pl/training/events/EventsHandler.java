package pl.training.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
@Log
@RequiredArgsConstructor
public class EventsHandler implements WebSocketHandler {

    private final ObjectMapper mapper;
    private final EventSource eventSource;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        var events = eventSource.periodicEvents.map(this::toJson).map(session::textMessage);
        return session.send(events).and(session.receive().map(WebSocketMessage::getPayloadAsText).doOnNext(this::onMessage));
    }

    private void onMessage(String message) {
        log.info("New message: " + message);
    }

    @SneakyThrows
    private String toJson(Event event) {
        return mapper.writeValueAsString(event);
    }

}
