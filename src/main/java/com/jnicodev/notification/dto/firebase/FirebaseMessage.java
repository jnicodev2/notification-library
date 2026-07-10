package com.jnicodev.notification.dto.firebase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseMessage {

    private final String token;
    private final FirebaseNotification notification;
    private final Map<String, String> data;

    private FirebaseMessage(Builder builder) {
        this.token = builder.token;
        this.notification = builder.notification;
        this.data = builder.data;
    }

    public String toJson() {

        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"token\": \"").append(token).append("\",\n");

        json.append("  \"notification\": {\n");
        json.append("      \"title\": \"")
                .append(notification.getTitle())
                .append("\",\n");
        json.append("      \"body\": \"")
                .append(notification.getBody())
                .append("\"\n");
        json.append("  }");

        if (!data.isEmpty()) {

            json.append(",\n  \"data\": {");

            int i = 0;

            for (Map.Entry<String, String> entry : data.entrySet()) {

                json.append("\"")
                        .append(entry.getKey())
                        .append("\":\"")
                        .append(entry.getValue())
                        .append("\"");

                if (++i < data.size()) {
                    json.append(",");
                }

            }

            json.append("}");
        }

        json.append("\n}");

        return json.toString();

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String token;
        private FirebaseNotification notification;
        private final Map<String, String> data = new HashMap<>();

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder notification(FirebaseNotification notification) {
            this.notification = notification;
            return this;
        }

        public Builder data(String key, String value) {
            data.put(key, value);
            return this;
        }

        public FirebaseMessage build() {
            return new FirebaseMessage(this);
        }

    }

}
