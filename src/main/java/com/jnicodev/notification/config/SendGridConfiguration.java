package com.jnicodev.notification.config;

import java.util.Objects;

public class SendGridConfiguration {

    private final String apiKey;
    private final String fromEmail;

    private SendGridConfiguration(Builder builder) {
        this.apiKey = builder.apiKey;
        this.fromEmail = builder.fromEmail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String apiKey;
        private String fromEmail;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder from(String fromEmail) {
            this.fromEmail = fromEmail;
            return this;
        }

        public SendGridConfiguration build() {
            return new SendGridConfiguration(this);
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getFromEmail() {
        return fromEmail;
    }
}
