package com.jnicodev.notification.dto.twilio;

public class TwilioMessage {

    private final TwilioPhoneNumber from;
    private final TwilioPhoneNumber to;
    private final String body;

    private TwilioMessage(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.body = builder.body;
    }

    public String getBody() {
        return body;
    }

    public TwilioPhoneNumber getFrom() {
        return from;
    }

    public TwilioPhoneNumber getTo() {
        return to;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private TwilioPhoneNumber from;
        private TwilioPhoneNumber to;
        private String body;

        public Builder from(TwilioPhoneNumber from) {
            this.from = from;
            return this;
        }

        public Builder to(TwilioPhoneNumber to) {
            this.to = to;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public TwilioMessage build() {
            return new TwilioMessage(this);
        }
    }

}
