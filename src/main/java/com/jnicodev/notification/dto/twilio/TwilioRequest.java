package com.jnicodev.notification.dto.twilio;

public class TwilioRequest {

    private final TwilioMessage message;

    public TwilioRequest(TwilioMessage message) {
        this.message = message;
    }

    public TwilioMessage getMessage() {
        return message;
    }

}
