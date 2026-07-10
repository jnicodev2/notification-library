package com.jnicodev.notification.dto;

public class NotificationRequest {

    private final String recipient;
    private final NotificationContent content;

    private NotificationRequest(Builder builder) {
        this.recipient = builder.recipient;
        this.content = builder.content;
    }

    public String getRecipient() {
        return recipient;
    }

    public NotificationContent getContent() {
        return content;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String recipient;
        private NotificationContent content;

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder content(NotificationContent content) {
            this.content = content;
            return this;
        }

        public NotificationRequest build() {
            return new NotificationRequest(this);
        }
    }

}
