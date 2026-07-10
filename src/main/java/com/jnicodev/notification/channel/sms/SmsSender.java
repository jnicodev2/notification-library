package com.jnicodev.notification.channel.sms;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;

public interface SmsSender {

    NotificationResult send(NotificationRequest request);

}
