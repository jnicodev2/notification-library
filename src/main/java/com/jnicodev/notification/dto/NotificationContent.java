package com.jnicodev.notification.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NotificationContent {

    private final String title;
    private final String body;
    private final Map<String, Object> attributes;

    private NotificationContent(Builder builder) {
        this.title = builder.title;
        this.body = builder.body;
        this.attributes = Collections.unmodifiableMap(builder.attributes);
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String title;
        private String body;
        private final Map<String, Object> attributes = new HashMap<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder attribute(String key, Object value) {
            this.attributes.put(key, value);
            return this;
        }

        public Builder attributes(Map<String, Object> attributes) {
            this.attributes.putAll(attributes);
            return this;
        }

        public NotificationContent build() {
            return new NotificationContent(this);
        }
    }

}
