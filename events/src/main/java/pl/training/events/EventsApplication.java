package pl.training.events;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EventsApplication implements ApplicationRunner {

    private final EventSource eventSource;

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        eventSource.periodicEvents.subscribe(System.out::println);
    }

}
