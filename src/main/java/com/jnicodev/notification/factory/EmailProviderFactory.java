package com.jnicodev.notification.factory;

import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.channel.email.EmailNotificationChannel;
import com.jnicodev.notification.config.MailgunConfiguration;
import com.jnicodev.notification.config.SendGridConfiguration;
import com.jnicodev.notification.provider.email.mailgun.MailgunProvider;
import com.jnicodev.notification.provider.email.sendgrid.SendGridProvider;

public class EmailProviderFactory {

    public NotificationChannel sendGrid(SendGridConfiguration configuration) {
        return new EmailNotificationChannel(
                new SendGridProvider(configuration)
        );

    }

    public NotificationChannel mailgun(MailgunConfiguration configuration) {

        return new EmailNotificationChannel(
                new MailgunProvider(configuration)
        );

    }

}
