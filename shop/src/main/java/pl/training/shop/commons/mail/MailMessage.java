package pl.training.shop.commons.mail;

import lombok.Value;

import java.util.List;

@Value
public class MailMessage {

    List<String> recipients;
    String title;
    String text;

}
