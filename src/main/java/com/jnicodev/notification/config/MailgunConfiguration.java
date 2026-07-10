package com.jnicodev.notification.config;

import java.util.Objects;

public class MailgunConfiguration {

    private final String apiKey;
    private final String domain;
    private final String from;

    private MailgunConfiguration(Builder builder) {
        this.apiKey = builder.apiKey;
        this.domain = builder.domain;
        this.from = builder.from;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getDomain() {
        return domain;
    }

    public String getFrom() {
        return from;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String apiKey;
        private String domain;
        private String from;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public MailgunConfiguration build() {
            return new MailgunConfiguration(this);
        }

    }

}
