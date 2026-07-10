package com.jnicodev.notification.builder;

import com.jnicodev.notification.service.NotificationService;
import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.service.DefaultNotificationService;

import java.util.Objects;

public class NotificationServiceBuilder {

    private NotificationChannel channel;

    private NotificationServiceBuilder() {
    }

    public static NotificationServiceBuilder builder() {
        return new NotificationServiceBuilder();
    }

    public NotificationServiceBuilder channel(NotificationChannel channel) {
        this.channel = channel;
        return this;
    }

    public NotificationService build() {

        Objects.requireNonNull(
                channel,
                "Debe configurar un canal de notificación."
        );

        return new DefaultNotificationService(channel);

    }

}
