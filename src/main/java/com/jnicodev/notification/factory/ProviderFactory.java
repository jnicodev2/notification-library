package com.jnicodev.notification.factory;

public final class ProviderFactory {

    private ProviderFactory() {
    }

    public static EmailProviderFactory email() {
        return new EmailProviderFactory();
    }

    public static SmsProviderFactory sms() {
        return new SmsProviderFactory();
    }

    public static PushProviderFactory push() {
        return new PushProviderFactory();
    }

}
