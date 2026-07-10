package com.jnicodev.notification.dto.mailgun;

public class MailgunResponse {

    private final int statusCode;
    private final String body;
    private final String id;

    public MailgunResponse(int statusCode,
                           String body,
                           String id) {

        this.statusCode = statusCode;
        this.body = body;
        this.id = id;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public String getId() {
        return id;
    }

    public boolean isSuccess() {
        return statusCode == 200;
    }

}
