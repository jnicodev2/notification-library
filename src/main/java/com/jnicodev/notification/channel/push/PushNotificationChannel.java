package com.jnicodev.notification.channel.push;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.NotificationChannel;

public class PushNotificationChannel implements NotificationChannel {

    private final PushSender sender;

    public PushNotificationChannel(PushSender sender) {
        this.sender = sender;
    }

    @Override
    public NotificationResult send(NotificationRequest request) {
        return sender.send(request);
    }

}
