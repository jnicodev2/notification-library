package com.jnicodev.notification.service;

import com.jnicodev.notification.dto.NotificationRequest;
import com.jnicodev.notification.dto.NotificationResult;

public interface NotificationService {

    NotificationResult send(NotificationRequest request);

}
