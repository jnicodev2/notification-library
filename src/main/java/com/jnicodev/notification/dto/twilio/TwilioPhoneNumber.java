package com.jnicodev.notification.dto.twilio;

public class TwilioPhoneNumber {

    private final String value;

    public TwilioPhoneNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
