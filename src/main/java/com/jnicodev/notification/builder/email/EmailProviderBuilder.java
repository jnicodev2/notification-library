package com.jnicodev.notification.builder.email;

public class EmailProviderBuilder {

    public SendGridBuilder sendGrid() {
        return new SendGridBuilder();
    }

    public MailgunBuilder mailgun() {
        return new MailgunBuilder();
    }

}
