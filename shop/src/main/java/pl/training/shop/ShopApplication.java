package pl.training.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.training.shop.commons.mail.MailMessage;
import pl.training.shop.commons.mail.MailService;
import pl.training.shop.commons.mail.TemplateService;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class ShopApplication implements ApplicationRunner {

    private final MailService mailService;
    private final TemplateService templateService;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var mailText = templateService.evaluate("invitation", Map.of("username", "Jan"), "pl");
        var mailMessage = new MailMessage(List.of("jan@training.pl"), "Invitation", mailText);
        mailService.send(mailMessage);
    }


}
