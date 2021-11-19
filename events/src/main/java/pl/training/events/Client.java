package pl.training.events;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;

public class Client {

    public static void main(String[] args) {
        var client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/events"), session -> {
            var message = Mono.just(session.textMessage("Desktop client connected"));
            return session.send(message)
                    .thenMany(session.receive().map(WebSocketMessage::getPayloadAsText).log())
                    .then();
        }).block();
    }

}
