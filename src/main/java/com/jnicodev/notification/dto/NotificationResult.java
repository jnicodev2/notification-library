package com.jnicodev.notification.dto;

public class NotificationResult {

    private final boolean success;
    private final String provider;
    private final String message;

    public NotificationResult(boolean success,
                              String provider,
                              String message) {
        this.success = success;
        this.provider = provider;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getProvider() {
        return provider;
    }

    public String getMessage() {
        return message;
    }
}
