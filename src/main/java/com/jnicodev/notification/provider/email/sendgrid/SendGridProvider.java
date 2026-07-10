package com.jnicodev.notification.provider.email.sendgrid;


import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.email.EmailSender;
import com.jnicodev.notification.config.SendGridConfiguration;
import com.jnicodev.notification.dto.sendgrid.SendGridEmail;
import com.jnicodev.notification.exception.DeliveryException;
import com.jnicodev.notification.dto.sendgrid.SendGridMail;
import com.jnicodev.notification.dto.sendgrid.SendGridRequest;
import com.jnicodev.notification.dto.sendgrid.SendGridResponse;

public class SendGridProvider implements EmailSender {

    private final SendGridClient client;
    private final SendGridConfiguration configuration;

    public SendGridProvider(SendGridConfiguration configuration) {

        this.configuration = configuration;
        this.client = new SendGridClient(configuration);

    }

    @Override
    public NotificationResult send(NotificationRequest request) {

        SendGridMail mail = new SendGridMail(
                        new SendGridEmail(configuration.getFromEmail()),
                        new SendGridEmail(request.getRecipient()),
                        request.getContent().getTitle(),
                        "text/plain",
                        request.getContent().getBody()
                );

        SendGridRequest sgRequest = new SendGridRequest(
                        "POST",
                        "/mail/send",
                        mail.toJson()
                );

        SendGridResponse response = client.send(sgRequest);

        if (!response.isSuccess()) {
            throw new DeliveryException(
                    "SendGrid",
                    "No fue posible enviar el correo. Código HTTP: " + response.getStatusCode()

            );

        }

        return new NotificationResult(
                response.isSuccess(),
                "SendGrid",
                response.getBody()
        );

    }

}
