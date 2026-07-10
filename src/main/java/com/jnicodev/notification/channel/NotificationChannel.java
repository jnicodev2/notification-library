package com.jnicodev.notification.channel;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;

public interface NotificationChannel {

    NotificationResult send(NotificationRequest request);

}
