package com.jnicodev.notification.channel.email;


import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.NotificationChannel;

public class EmailNotificationChannel implements NotificationChannel {

    private final EmailSender sender;

    public EmailNotificationChannel(EmailSender sender) {
        this.sender = sender;
    }

    @Override
    public NotificationResult send(NotificationRequest request) {
        return sender.send(request);
    }
}
