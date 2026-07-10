package com.jnicodev.notification.builder.email;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.config.SendGridConfiguration;
import com.jnicodev.notification.factory.ProviderFactory;

public class SendGridBuilder {

    private String apiKey;
    private String from;

    public SendGridBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public SendGridBuilder from(String from) {
        this.from = from;
        return this;
    }

    public NotificationChannel build() {

        SendGridConfiguration configuration =
                SendGridConfiguration.builder()
                        .apiKey(apiKey)
                        .from(from)
                        .build();

        return ProviderFactory.email()
                .sendGrid(configuration);

    }

}
