package com.jnicodev.notification.dto.sendgrid;

import com.jnicodev.notification.dto.NotificationRequest;

public class SendGridMail {

    private final SendGridEmail from;
    private final SendGridEmail to;
    private final String subject;
    private final String contentType;
    private final String content;

    public SendGridMail(SendGridEmail from,
                        SendGridEmail to,
                        String subject,
                        String contentType,
                        String content) {

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.contentType = contentType;
        this.content = content;
    }

    public static SendGridMail from(NotificationRequest request) {

        return new SendGridMail(
                new SendGridEmail("noreply@company.com"),
                new SendGridEmail(request.getRecipient()),
                request.getContent().getTitle(),
                "text/plain",
                request.getContent().getBody()
        );

    }

    public String toJson() {

        return String.format("""
                {
                  "to": {
                    "email": "%s"
                  },
                  "from": {
                    "email": "%s"
                  },
                  "subject": "%s",
                  "content": [
                    {
                      "type": "%s",
                      "value": "%s"
                    }
                  ]
                }
                """,
                to.getEmail(),
                from.getEmail(),
                subject,
                contentType,
                content
        );
    }

}
