package com.jnicodev.notification.channel.email;


import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;

public interface EmailSender {

    NotificationResult send(NotificationRequest request);
}
