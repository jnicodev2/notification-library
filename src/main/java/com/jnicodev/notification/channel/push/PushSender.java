package com.jnicodev.notification.channel.push;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;

public interface PushSender {

    NotificationResult send(NotificationRequest request);

}
