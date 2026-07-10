package com.jnicodev.notification.provider.sms.twilio;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.sms.SmsSender;
import com.jnicodev.notification.config.TwilioConfiguration;
import com.jnicodev.notification.dto.twilio.TwilioMessage;
import com.jnicodev.notification.dto.twilio.TwilioPhoneNumber;
import com.jnicodev.notification.dto.twilio.TwilioRequest;
import com.jnicodev.notification.dto.twilio.TwilioResponse;
import com.jnicodev.notification.exception.DeliveryException;

public class TwilioProvider implements SmsSender {

    private final TwilioClient client;
    private final TwilioConfiguration configuration;

    public TwilioProvider(TwilioConfiguration configuration) {

        this.configuration = configuration;
        this.client = new TwilioClient(configuration);

    }

    @Override
    public NotificationResult send(NotificationRequest request) {

        TwilioMessage message =
                TwilioMessage.builder()
                        .from(new TwilioPhoneNumber(configuration.getFrom()))
                        .to(new TwilioPhoneNumber(request.getRecipient()))
                        .body(request.getContent().getBody())
                        .build();

        TwilioResponse response =
                client.send(
                        new TwilioRequest(message)
                );

        if (!response.isSuccess()) {

            throw new DeliveryException(
                    "Twilio",
                    "Error enviando SMS."
            );

        }

        return new NotificationResult(
                true,
                "Twilio",
                response.getStatus()
        );

    }

}
