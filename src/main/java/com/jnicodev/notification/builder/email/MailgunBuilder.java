package com.jnicodev.notification.builder.email;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.config.MailgunConfiguration;
import com.jnicodev.notification.factory.ProviderFactory;

public class MailgunBuilder {

    private String apiKey;
    private String domain;
    private String from;

    public MailgunBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public MailgunBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public MailgunBuilder from(String from) {
        this.from = from;
        return this;
    }

    public NotificationChannel build() {

        MailgunConfiguration configuration = MailgunConfiguration.builder()
                        .apiKey(apiKey)
                        .domain(domain)
                        .from(from)
                        .build();

        return ProviderFactory.email()
                .mailgun(configuration);

    }

}
