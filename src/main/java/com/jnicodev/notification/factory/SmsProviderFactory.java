package com.jnicodev.notification.factory;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.channel.sms.SmsNotificationChannel;
import com.jnicodev.notification.config.TwilioConfiguration;
import com.jnicodev.notification.provider.sms.twilio.TwilioProvider;

public class SmsProviderFactory {

    public NotificationChannel twilio(TwilioConfiguration configuration) {

        return new SmsNotificationChannel(
                new TwilioProvider(configuration)
        );

    }

}
