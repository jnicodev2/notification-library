package com.jnicodev.notification.dto.firebase;

public class FirebaseNotification {

    private final String title;
    private final String body;

    private FirebaseNotification(Builder builder) {
        this.title = builder.title;
        this.body = builder.body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String title;
        private String body;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public FirebaseNotification build() {
            return new FirebaseNotification(this);
        }

    }

}
