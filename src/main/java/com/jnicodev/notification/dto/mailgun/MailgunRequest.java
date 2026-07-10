package com.jnicodev.notification.dto.mailgun;

public class MailgunRequest {

    private final String endpoint;
    private final String authorization;
    private final String body;

    public MailgunRequest(String endpoint,
                          String authorization,
                          String body) {

        this.endpoint = endpoint;
        this.authorization = authorization;
        this.body = body;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAuthorization() {
        return authorization;
    }

    public String getBody() {
        return body;
    }

}
