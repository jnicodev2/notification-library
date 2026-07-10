package com.jnicodev.notification.dto.firebase;

public class FirebaseResponse {

    private final int statusCode;
    private final String messageId;

    public FirebaseResponse(int statusCode,
                            String messageId) {

        this.statusCode = statusCode;
        this.messageId = messageId;
    }

    public boolean isSuccess() {
        return statusCode == 200;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessageId() {
        return messageId;
    }

}
