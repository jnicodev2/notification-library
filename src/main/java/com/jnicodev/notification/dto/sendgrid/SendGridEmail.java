package com.jnicodev.notification.dto.sendgrid;

public class SendGridEmail {

    private final String email;

    public SendGridEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
