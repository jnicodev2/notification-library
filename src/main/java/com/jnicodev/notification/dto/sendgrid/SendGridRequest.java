package com.jnicodev.notification.dto.sendgrid;

public class SendGridRequest {

    private final String method;
    private final String endpoint;
    private final String body;

    public SendGridRequest(String method,
                           String endpoint,
                           String body) {

        this.method = method;
        this.endpoint = endpoint;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBody() {
        return body;
    }

}
