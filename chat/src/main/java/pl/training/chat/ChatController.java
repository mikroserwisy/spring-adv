package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Log
@RequiredArgsConstructor
public class ChatController implements ApplicationListener<SessionConnectedEvent> {

    private final Map<String, String> clients = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void onMessage(@Payload Message message, @Header("simpSessionId") String sessionId) {
        message.setTimestamp(LocalTime.now());
        log.info("New message: " + message + " from " + sessionId);
        if (message.getRecipient().equals("all")) {
            messagingTemplate.convertAndSend("/main-room/messages", message);
        } else {
            messagingTemplate.convertAndSend("/private-room/user" + clients.get(message.getRecipient()) , message);
            messagingTemplate.convertAndSend("/private-room/user" + clients.get(message.getSender()) , message);
        }
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        var headers = event.getMessage().getHeaders();
        var sessionId = headers.get("simpSessionId").toString();
        var nativeHeaders = (Map<String, List<String>>) ((GenericMessage)headers.get("simpConnectMessage")).getHeaders().get("nativeHeaders");
        var user = nativeHeaders.get("user").get(0);
        clients.put(user, sessionId);
    }

    /*@MessageMapping("/chat")
    @SendTo("/main-room/messages")
    public Message onMessage(Message message) {
        message.setTimestamp(LocalTime.now());
        log.info("New message: " + message);
        return message;
    }*/

}
