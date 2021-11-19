package pl.training.events;

import lombok.extern.java.Log;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        var client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/events"), new Handler()).subscribe();
        Thread.sleep(10_000);
    }

}

@Log
class Handler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(this::onMessage)
                .then();
    }

    private void onMessage(String message) {
        log.info(message);
    }

}
