package com.jnicodev.notification.config;

public class FirebaseConfiguration {

    private final String projectId;

    private FirebaseConfiguration(Builder builder) {
        this.projectId = builder.projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String projectId;

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public FirebaseConfiguration build() {
            return new FirebaseConfiguration(this);
        }

    }

}
