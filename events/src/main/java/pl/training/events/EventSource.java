package pl.training.events;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class EventSource {

    private final Random random = new Random();

    private final Flux<Long> ticks = Flux.interval(Duration.ofSeconds(3));
    private final Flux<Event> events = Flux.generate(sink -> sink.next(createEvent()));

    final Flux<Event> periodicEvents = ticks.zipWith(events, (index, event) -> event);

    private Event createEvent() {
        return new Event(UUID.randomUUID(), LocalDateTime.now(), random.nextLong());
    }

}
