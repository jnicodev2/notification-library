package com.jnicodev.notification.dto.sendgrid;

public class SendGridResponse {

    private final int statusCode;
    private final String body;
    private final String messageId;

    public SendGridResponse(int statusCode,
                            String body,
                            String messageId) {

        this.statusCode = statusCode;
        this.body = body;
        this.messageId = messageId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public String getMessageId() {
        return messageId;
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }

}
