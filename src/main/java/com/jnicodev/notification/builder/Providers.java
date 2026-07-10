package com.jnicodev.notification.builder;

import com.jnicodev.notification.builder.email.EmailProviderBuilder;
import com.jnicodev.notification.builder.push.PushProviderBuilder;
import com.jnicodev.notification.builder.sms.SmsProviderBuilder;

public final class Providers {

    private Providers() {
    }

    public static EmailProviderBuilder email() {
        return new EmailProviderBuilder();
    }

    public static SmsProviderBuilder sms() {
        return new SmsProviderBuilder();
    }

    public static PushProviderBuilder push() {
        return new PushProviderBuilder();
    }

}
