package com.jnicodev.notification.builder;

import com.jnicodev.notification.service.NotificationService;
import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.channel.email.EmailNotificationChannel;
import com.jnicodev.notification.channel.email.EmailSender;
import com.jnicodev.notification.channel.push.PushNotificationChannel;
import com.jnicodev.notification.channel.push.PushSender;
import com.jnicodev.notification.channel.sms.SmsNotificationChannel;
import com.jnicodev.notification.channel.sms.SmsSender;
import com.jnicodev.notification.service.DefaultNotificationService;

public class NotificationServiceBuilder {

    private NotificationChannel channel;

    private NotificationServiceBuilder() {
    }

    public static NotificationServiceBuilder builder() {
        return new NotificationServiceBuilder();
    }

    public NotificationServiceBuilder email(EmailSender sender) {

        this.channel = new EmailNotificationChannel(sender);

        return this;
    }

    public NotificationServiceBuilder sms(
            SmsSender sender) {

        this.channel = new SmsNotificationChannel(sender);

        return this;
    }

    public NotificationServiceBuilder push(
            PushSender sender) {

        this.channel =
                new PushNotificationChannel(sender);

        return this;
    }

    public NotificationService build() {

        if (channel == null) {
            throw new IllegalStateException(
                    "Debe configurar un canal."
            );
        }

        return new DefaultNotificationService(channel);

    }

}
