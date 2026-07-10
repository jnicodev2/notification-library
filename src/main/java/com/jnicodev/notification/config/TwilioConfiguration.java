package com.jnicodev.notification.config;

public class TwilioConfiguration {

    private final String accountSid;
    private final String authToken;
    private final String from;

    private TwilioConfiguration(Builder builder) {
        this.accountSid = builder.accountSid;
        this.authToken = builder.authToken;
        this.from = builder.from;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getFrom() {
        return from;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String accountSid;
        private String authToken;
        private String from;

        public Builder accountSid(String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public Builder authToken(String authToken) {
            this.authToken = authToken;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public TwilioConfiguration build() {
            return new TwilioConfiguration(this);
        }
    }
}
