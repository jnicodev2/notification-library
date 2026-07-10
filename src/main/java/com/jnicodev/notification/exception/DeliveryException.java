package com.jnicodev.notification.exception;

public class DeliveryException extends NotificationException {

    private final String provider;

    public DeliveryException(String provider,
                             String message) {

        super(message);
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

}
