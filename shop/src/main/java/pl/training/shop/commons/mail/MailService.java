package pl.training.shop.commons.mail;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String UTF_8_ENCODING = "utf-8";

    private final JavaMailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final Queue mailQueue;
    @Value("${mail.sender}")
    @Setter
    private String sender;

    public void send(MailMessage message) {
        jmsTemplate.send(mailQueue, session -> session.createObjectMessage(message));
    }

    private MimeMessagePreparator mimeMessagePreparator(MailMessage message) {
        return mimeMessage -> {
            var mailMessageHelper = new MimeMessageHelper(mimeMessage, UTF_8_ENCODING);
            mailMessageHelper.setFrom(sender);
            mailMessageHelper.setTo(message.getRecipients().toArray(new String[0]));
            mailMessageHelper.setSubject(message.getTitle());
            mailMessageHelper.setText(message.getText(), true);
        };
    }

    @JmsListener(destination = "Mail", containerFactory = "queueContainerFactory")
    void onMailMessage(MailMessage message) {
        mailSender.send(mimeMessagePreparator(message));
    }

}
