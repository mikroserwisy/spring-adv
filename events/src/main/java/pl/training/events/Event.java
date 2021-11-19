package pl.training.events;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class Event {

    UUID uuid;
    LocalDateTime timestamp;
    Long value;

}
