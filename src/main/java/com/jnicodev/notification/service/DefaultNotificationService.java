package com.jnicodev.notification.service;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;
import com.jnicodev.notification.channel.NotificationChannel;
import com.jnicodev.notification.validation.NotificationValidator;

public class DefaultNotificationService implements NotificationService {

    private final NotificationChannel channel;
    private final NotificationValidator validator = new NotificationValidator();

    public DefaultNotificationService(NotificationChannel channel) {
        this.channel = channel;
    }

    @Override
    public NotificationResult send(NotificationRequest request) {

        validator.validate(request);

        return channel.send(request);

    }
}


