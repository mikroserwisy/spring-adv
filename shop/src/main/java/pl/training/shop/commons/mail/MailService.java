package pl.training.shop.commons.mail;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String UTF_8_ENCODING = "utf-8";

    private final JavaMailSender mailSender;
    @Value("${mail.sender}")
    @Setter
    private String sender;

    public void send(MailMessage message) {
        mailSender.send(mimeMessagePreparator(message));
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

}
