package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Log
@RequiredArgsConstructor
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/main-room/messages")
    public Message onMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        log.info("New message: " + message);
        return message;
    }

}
