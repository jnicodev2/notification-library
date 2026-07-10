package com.jnicodev.notification.provider.email.mailgun;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.email.EmailSender;
import com.jnicodev.notification.config.MailgunConfiguration;
import com.jnicodev.notification.dto.mailgun.MailgunMessage;
import com.jnicodev.notification.dto.mailgun.MailgunRequest;
import com.jnicodev.notification.dto.mailgun.MailgunResponse;
import com.jnicodev.notification.exception.DeliveryException;

public class MailgunProvider implements EmailSender {

    private final MailgunClient client;
    private final MailgunConfiguration configuration;

    public MailgunProvider(MailgunConfiguration configuration) {

        this.configuration = configuration;
        this.client = new MailgunClient(configuration);

    }

    @Override
    public NotificationResult send(NotificationRequest request) {

        MailgunMessage message =
                MailgunMessage.from(request, configuration);

        MailgunRequest mailgunRequest = new MailgunRequest(
                        "https://api.mailgun.net/v3/"
                                + configuration.getDomain()
                                + "/messages",
                        client.authorizationHeader(),
                        message.toFormData()
                );

        MailgunResponse response = client.send(mailgunRequest);

        if (!response.isSuccess()) {
            throw new DeliveryException(
                    "Mailgun",
                    "Error HTTP " + response.getStatusCode()
            );

        }

        return new NotificationResult(
                true,
                "Mailgun",
                response.getBody()
        );

    }

}
