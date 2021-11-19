package pl.training.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

    private String sender;
    private String recipient;
    private String text;
    private LocalDateTime timestamp;

}
