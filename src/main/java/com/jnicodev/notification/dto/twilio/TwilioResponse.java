package com.jnicodev.notification.dto.twilio;

public class TwilioResponse {

    private final int statusCode;
    private final String sid;
    private final String status;

    public TwilioResponse(int statusCode,
                          String sid,
                          String status) {

        this.statusCode = statusCode;
        this.sid = sid;
        this.status = status;
    }

    public boolean isSuccess() {
        return statusCode == 201;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getSid() {
        return sid;
    }

    public String getStatus() {
        return status;
    }

}
