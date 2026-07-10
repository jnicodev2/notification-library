package com.jnicodev.notification.dto.firebase;

public class FirebaseRequest {

    private final String endpoint;
    private final String body;

    public FirebaseRequest(String endpoint,
                           String body) {

        this.endpoint = endpoint;
        this.body = body;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBody() {
        return body;
    }

}
