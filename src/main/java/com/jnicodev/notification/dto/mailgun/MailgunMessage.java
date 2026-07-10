package com.jnicodev.notification.dto.mailgun;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.config.MailgunConfiguration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MailgunMessage {

    private final String from;
    private final String to;
    private final String subject;
    private final String text;

    public MailgunMessage(String from,
                          String to,
                          String subject,
                          String text) {

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public static MailgunMessage from(NotificationRequest request,
                                      MailgunConfiguration configuration) {

        return new MailgunMessage(
                configuration.getFrom(),
                request.getRecipient(),
                request.getContent().getTitle(),
                request.getContent().getBody()
        );
    }

    public String toFormData() {

        return "from=" + encode(from)
                + "&to=" + encode(to)
                + "&subject=" + encode(subject)
                + "&text=" + encode(text);
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

}
