package com.jnicodev.notification.builder.sms;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.config.TwilioConfiguration;
import com.jnicodev.notification.factory.ProviderFactory;

public class TwilioBuilder {

    private String accountSid;
    private String authToken;
    private String from;

    public TwilioBuilder accountSid(String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    public TwilioBuilder authToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public TwilioBuilder from(String from) {
        this.from = from;
        return this;
    }

    public NotificationChannel build() {

        TwilioConfiguration configuration =
                TwilioConfiguration.builder()
                        .accountSid(accountSid)
                        .authToken(authToken)
                        .from(from)
                        .build();

        return ProviderFactory.sms()
                .twilio(configuration);

    }

}
