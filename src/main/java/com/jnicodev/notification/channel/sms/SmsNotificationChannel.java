package com.jnicodev.notification.channel.sms;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.NotificationChannel;

public class SmsNotificationChannel implements NotificationChannel {

    private final SmsSender sender;

    public SmsNotificationChannel(SmsSender sender) {
        this.sender = sender;
    }

    @Override
    public NotificationResult send(NotificationRequest request) {
        return sender.send(request);
    }

}
